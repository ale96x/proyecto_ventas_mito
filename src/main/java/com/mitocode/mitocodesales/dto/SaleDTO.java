package com.mitocode.mitocodesales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.mitocodesales.model.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {
    private Integer idSale;

    @JsonIncludeProperties(value = {"idClient"})  //Solo quiero incluir en el Json de Sale el atributo idClient del objeto ClientDTO
    private ClientDTO client;

    @JsonIncludeProperties(value = {"idUser", "username"})
    private UserDTO user;
    private LocalDateTime datetime;
    private double total;
    private double tax;
    private boolean enabled;

    @JsonManagedReference
    private List<SaleDetailDTO> details;
}
