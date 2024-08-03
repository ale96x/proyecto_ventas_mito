package com.mitocode.secutiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//Clase S3
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}
