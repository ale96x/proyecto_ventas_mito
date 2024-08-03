package com.mitocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean  //Indicamos que no se genere la instancia de JpaRepository porque T no es un @Entity
public interface IGenericRepo<T,ID> extends JpaRepository<T, ID> {
}
