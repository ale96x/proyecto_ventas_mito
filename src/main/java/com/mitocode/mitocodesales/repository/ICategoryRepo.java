package com.mitocode.mitocodesales.repository;

import com.mitocode.mitocodesales.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepo extends IGenericRepo<Category,Integer> {
    //Queries derivados
    //select * from category c where c.name = 'COMPUTERS';
    List<Category> findByName(String name);

    //select * from category c where c.name like '%COMPUTERS'
    List<Category> findByNameLike(String name);

    //Busca los registros donde en el nombre contenga el name
    List<Category> findByNameContains(String name);

    //select * from category c where c.name like 'COMPU%'
    List<Category> findByNameStartsWith(String name);

    //select * from category c where c.name like '%UTERS'
    List<Category> findByNameEndsWith(String name);

    //select * from category c where c.name = '{name}' and c.enabled = '{enabled}'
    List<Category> findByNameAndEnabled(String name, boolean enabled);

    //select * from category c where c.name = '{name}' or c.enabled = '{enabled}'
    List<Category> findByNameOrEnabled(String name, boolean enabled);

    //select * from category c where c.enabled = true
    List<Category> findByEnabledTrue();

    //select * from category c where c.enabled = false
    List<Category> findByEnabledFalse();

    //Encuentra solo el primer registro que cumpla con la condicion
    //select * from category c where c.name = 'COMPUTERS';
    //SELECT * FROM category c where c.name = 'COMPUTERS' LIMIT 1;
    Category findOneByName(String name);

    //Encuentra solo los 3 primeros registros que cumpla con la condicion
    //SELECT * FROM category c where c.name = 'COMPUTERS' LIMIT 3;
    List<Category> findTop3ByName(String name);

    //SELECT * FROM category c where c.name is null;
    List<Category> findByNameIs(String name);
    List<Category> findByNameIsNot(String name);
    List<Category> findByNameIsNull();
    List<Category> findByNameIsNotNull();
    List<Category> findByNameEquals(String name);
    List<Category> findByNameEqualsIgnoreCase(String name);

    //JPQL
    //Aqui usamos de acuerdo al orden de los parametros
    @Query("FROM Category c WHERE c.name = ?1 AND c.description LIKE %?2%")
    List<Category> getByAllNameAndDescription(String name, String description);

    //Aqui usamos el nombre de los parametros
    //Esta query devuelve todas las columnas de la consulta
    @Query("FROM Category c WHERE c.name = :name AND c.description LIKE %:description%")
    List<Category> getByAllNameAndDescription2(@Param("name") String name,@Param("description") String description);

    ///Si queremos usar consultas donde difiere el nro de columnas de los registros con el nro de atributos de la @Entidad se debe
    //crear un constructor capaz de recibir esa cantidad de columnas y construir el objeto
    @Query("SELECT new Category(c.name, c.description) FROM Category c")
    List<Category> getByNameAndDescription3();

    //SQL Native Queries
    @Query(nativeQuery = true, value = "select * from category where name = :name")
    List<Category> getByNameSQL(@Param("name") String name);

    //Cuando se realiza sentencias de modificacion INSERT,UPDATE,DELETE se debe utilizar @Modifyng
    @Modifying
    @Query(nativeQuery = true, value = "update category set name = :name where id_category = :id")
    Integer updateNameByIdSQL(@Param("name") String name, @Param("id") Integer id);



}