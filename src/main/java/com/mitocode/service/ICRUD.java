package com.mitocode.service;

import java.util.List;

public interface ICRUD<T, ID>{
    T create(T t) throws Exception; //Crea una categoria y devuelve ka misma categoria
    T update(T t) throws Exception;  //Actualiza una categoria y devuelve la misma categoria

    List<T> readAll() throws Exception; //Devuelve una lista de categorias

    T readById(ID id) throws Exception;

    void delete(ID id) throws Exception;
}
