package com.mitocode.mitocodesales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Usamos esta clase para hacer la consulta al procedimiento almacenado con @NamedStoredProcedureQuery
//JPA interface projection
public interface IProcedureDTO {
    Integer getQuantityFn();
    String getDatetimeFn();
}
