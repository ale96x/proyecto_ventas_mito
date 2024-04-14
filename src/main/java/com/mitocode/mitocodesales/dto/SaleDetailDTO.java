package com.mitocode.mitocodesales.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetailDTO {

    @JsonBackReference  //Con esta anotacion indicamos que no es necesario enviar este atributo en el json sino que ya se referencia que el json padre
    private SaleDTO sale;

    @JsonIncludeProperties(value = {"idProduct", "name"})
    private ProductDTO product;
    private short quantity;
    private double salePrice;
    private double discount;
}
