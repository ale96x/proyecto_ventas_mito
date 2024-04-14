package com.mitocode.mitocodesales.service.impl;

import com.mitocode.mitocodesales.dto.IProcedureDTO;
import com.mitocode.mitocodesales.dto.ProcedureDTO;
import com.mitocode.mitocodesales.model.Sale;
import com.mitocode.mitocodesales.model.SaleDetail;
import com.mitocode.mitocodesales.repository.ISaleRepo;
import com.mitocode.mitocodesales.repository.IGenericRepo;
import com.mitocode.mitocodesales.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    @Autowired
    private ISaleRepo repo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return this.repo;
    }

    //Llamada al procedimiento almacenado de forma nativa
    @Override
    public List<ProcedureDTO> callProcedure() {
        //Debe devolver un List<Object[]>
        //Ejemplo asi: [4, "28/09/2022"]
        List<ProcedureDTO> list = new ArrayList<>();
        repo.callProcedure().forEach(e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityFn((Integer) e[0]);
            dto.setDatetimeFn((String) e[1]);
            list.add(dto);
        });
        return list;
    }

    //Metodo con NamedNativeQuery es decir con las anotaciones @NamedNativeQuery y @SqlResultSetMapping
    @Override
    public List<ProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    //Llamada al procedimiento almacenado de con la anotacion @NamedStoredProcedureQuery en la entidad
    // y @Transactional en el metodo que invoca al metodo del procedimiento
    //Tambien se debe utilizar una interface como DTO
    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }

    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure4(Integer idClient) {
        return repo.callProcedure4(idClient);
    }

    //Consultas usando programacion funcional obteniendo todos los registros desde la base de datos y filtrando
    @Override
    public Sale getSaleMostExpensive() {
        return repo.findAll()
                .stream()
                .max(Comparator.comparing(e -> e.getTotal()))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerPerson() {
        Map<String, Double> byUser = repo.findAll()
                .stream()
                .collect(Collectors.groupingBy(s -> s.getUser().getUsername(), Collectors.summingDouble(e -> e.getTotal())));
        String user = Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
        return user;
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        return repo.findAll()
                .stream()
                .collect(Collectors.groupingBy(s -> s.getUser().getUsername(), Collectors.counting()));
    }

    @Override
    public Map<String, Double> getMostSellerProduct() {  //Primer paso es obtener los saleDetails pero teniendo los sales
        Stream<List<SaleDetail>> stream = repo.findAll().stream()   //Stream<Sale>
                .map(s -> s.getDetails());   //Stream<List<SaleDetail>>
        Stream<SaleDetail> streamDetails = stream.flatMap(list -> list.stream());

        Map<String, Double> byProduct = streamDetails
                .collect(Collectors.groupingBy(d -> d.getProduct().getName(), Collectors.summingDouble(e -> e.getQuantity())));

        return byProduct.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new)
                );
    }


}
