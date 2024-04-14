package com.mitocode.mitocodesales.service;

import com.mitocode.mitocodesales.dto.IProcedureDTO;
import com.mitocode.mitocodesales.dto.ProcedureDTO;
import com.mitocode.mitocodesales.model.Sale;

import java.util.List;
import java.util.Map;


public interface ISaleService extends ICRUD<Sale, Integer>{
    //Llamada al procedimiento almacenado de forma nativa
    List<ProcedureDTO> callProcedure();

    //Metodo con NamedNativeQuery es decir con las anotaciones NamedNativeQuery y SqlResultSetMapping
    List<ProcedureDTO> callProcedure2();

    //Llamada al procedimiento almacenado de con la anotacion @NamedStoredProcedureQuery en la entidad
    // y @Transactional en el metodo que invoca al metodo del procedimiento
    //Tambien se debe utilizar una interface como DTO
    List<IProcedureDTO> callProcedure3();

    List<IProcedureDTO> callProcedure4(Integer idClient);

    //Consultas usando programacion funcional obteniendo todos los registros desde la base de datos y filtrando
    Sale getSaleMostExpensive();

    String getBestSellerPerson();

    Map<String, Long> getSalesCountBySeller();//cantidad de ventas por vendedor

    Map<String, Double> getMostSellerProduct(); //Obtener el producto mas vendido
}
