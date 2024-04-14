package com.mitocode.mitocodesales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Integer idUser;
    private RoleDTO role;  //En el Json de User se debe recibir un Json de Role pero se puede indicar solo el idRole
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //Solo se puede escribir no leer
    private String password;
    private boolean enabled;
}
