package com.mitocode.mitocodesales.repository;

import com.mitocode.mitocodesales.dto.IProcedureDTO;
import com.mitocode.mitocodesales.dto.ProcedureDTO;
import com.mitocode.mitocodesales.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Llamada al procedimiento almacenado de forma nativa
public interface ISaleRepo extends IGenericRepo<Sale,Integer> {
    //Llamada a procedimientos almacenados
    //Se obtiene la cantidad de ventas por dia
    @Query(value = "SELECT * FROM fn_sales()", nativeQuery = true)
    List<Object[]> callProcedure();

    //Metodo con NamedNativeQuery es decir con las anotaciones NamedNativeQuery y SqlResultSetMapping
    @Query(nativeQuery = true, name = "Sale.fn_sales")
    List<ProcedureDTO> callProcedure2();

    //Llamada al procedimiento almacenado de con la anotacion @NamedStoredProcedureQuery en la entidad
    // y @Transactional en el metodo que invoca al metodo del procedimiento
    //Tambien se debe utilizar una interface como DTO
    @Procedure(procedureName = "fn_sales")
    List<IProcedureDTO> callProcedure3();

    //Llamada al procedimiento almacenado de con la anotacion @NamedStoredProcedureQuery en la entidad y uso de Parametros
    // y @Transactional en el metodo que invoca al metodo del procedimiento
    //Tambien se debe utilizar una interface como DTO
    @Procedure(procedureName = "fn_salesParameter")
    List<IProcedureDTO> callProcedure4(@Param("p_id_client") Integer idClient);
}
