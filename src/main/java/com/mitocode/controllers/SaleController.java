package com.mitocode.controllers;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.dto.SaleDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Sale;
import com.mitocode.service.ISaleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    @Qualifier("saleMapper")
    private ModelMapper mapper;

    @Autowired
    private ISaleService service;

    @GetMapping  //Se debe
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception{
        List<SaleDTO> listDTO = service.readAll()
                .stream()
                .map(sale -> mapper.map(sale, SaleDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);  //Este objeto puede ser nulo
        if(obj == null){
               throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@RequestBody @Valid SaleDTO dto) throws Exception{
        Sale obj = service.create(mapper.map(dto, Sale.class));
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.readById(dto.getIdSale());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + dto.getIdSale());
        }
        obj = service.update(mapper.map(dto, Sale.class));
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class), HttpStatus.ACCEPTED);
    }

    //Se puede usar tambien asi
    /*@PutMapping("/{id}")
    public Sale update(@PathVariable("id") Integer id ,@RequestBody Sale sale) throws Exception{
        sale.setIdSale(id);
        return service.update(sale);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        if(service.readById(id) == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Obtiene las cantidades de ventas por fecha
    //Llamada al procedimiento almacenado de forma nativa
    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> callProcedure() throws Exception{
        List<ProcedureDTO> listDTO = service.callProcedure();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    //Obtiene las cantidades de ventas por fecha
    //Metodo con NamedNativeQuery es decir con las anotaciones NamedNativeQuery y SqlResultSetMapping
    @GetMapping("/resume2")
    public ResponseEntity<List<ProcedureDTO>> callProcedure2() throws Exception{
        List<ProcedureDTO> listDTO = service.callProcedure2();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    //Obtiene las cantidades de ventas por fecha
    //Llamada al procedimiento almacenado de con la anotacion @NamedStoredProcedureQuery en la entidad
    // y @Transactional en el metodo que invoca al metodo del procedimiento
    @GetMapping("/resume3")
    public ResponseEntity<List<IProcedureDTO>> callProcedure3() throws Exception{
        List<IProcedureDTO> listDTO = service.callProcedure3();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    //Obtiene las cantidades de ventas por fecha
    //Llamada al procedimiento almacenado de con la anotacion @NamedStoredProcedureQuery en la entidad
    // y @Transactional en el metodo que invoca al metodo del procedimiento
    @GetMapping("/resume4/{id}")
    public ResponseEntity<List<IProcedureDTO>> callProcedure4(@PathVariable("id") Integer id) throws Exception{
        List<IProcedureDTO> listDTO = service.callProcedure4(id);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    //Consultas usando programacion funcional obteniendo todos los registros desde la base de datos y filtrando
    @GetMapping("/mostexpensive")
    public ResponseEntity<SaleDTO> getSaleMostExpensive() throws Exception{
        Sale sale = service.getSaleMostExpensive();
        return new ResponseEntity<>(mapper.map(sale, SaleDTO.class), HttpStatus.OK);
    }

    @GetMapping("/bestseller")
    public ResponseEntity<String> getBestSellerPerson() throws Exception{
        return new ResponseEntity<>(service.getBestSellerPerson(), HttpStatus.OK);
    }

    @GetMapping("/salescountseller")
    public ResponseEntity<Map<String, Long>> getSalesCountBySeller() throws Exception{
        Map<String, Long> map = service.getSalesCountBySeller();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/bestproduct")
    public ResponseEntity<Map<String, Double>> getProductMostSelled() throws Exception{
        Map<String, Double> map = service.getMostSellerProduct();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
