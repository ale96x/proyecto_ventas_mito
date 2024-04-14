package com.mitocode.mitocodesales.service;

import com.mitocode.mitocodesales.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryService extends ICRUD<Category, Integer>{  //Extendemos
    List<Category> findByName(String name);
    List<Category> findByNameLike(String name);
    List<Category> findByNameContains(String name);
    List<Category> findByNameStartsWith(String name);

    List<Category> findByNameEndsWith(String name);

    List<Category> findByNameAndEnabled(String name, boolean enabled);

    List<Category> findByNameOrEnabled(String name, boolean enabled);

    List<Category> getByNameAndDescription3();

    ////////// Paginacion y Ordenamiento //////////
    Page<Category> findPage(Pageable pageable);

    //Paginacion con Ordenamiento//
    List<Category> findAllOrder(String param);
}
