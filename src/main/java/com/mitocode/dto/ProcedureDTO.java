package com.mitocode.dto;

import lombok.*;

import java.time.LocalDateTime;

//Usamos esta clase para hacer la consulta al procedimiento almacenado
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcedureDTO {
    private Integer quantityFn;
    private String datetimeFn;
}
