package com.mitocode.mitocodesales.config;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean(name = "categoryMapper")
    public ModelMapper categoryMapper(){
        return new ModelMapper();
    }

    @Bean(name = "productMapper")
    public ModelMapper productMapper(){
        ModelMapper mapper = new ModelMapper();
        //Si se quiere mapear los datos del modelMapper se debe realizar esto
        /*TypeMap<ProductDTO, Product> typeMap = mapper.createTypeMap(ProductDTO.class, Product.class);
        typeMap.addMapping(ProductDTO::getIdCategory, (dest, v) -> dest.getCategory().setIdCategory((Integer) v));   //Asignamos el valor del Integer idCategory al idCategory del objeto del modelo
        typeMap.addMapping(e -> e.getIdProduct(), (dest, v) -> dest.setIdProduct((Integer) v));   //Asignamos el valor del idProducto del DTO al idProduct del modelo*/
        return mapper;
    }

    @Bean(name = "clientMapper")
    public ModelMapper clientMapper(){
        return new ModelMapper();
    }

    @Bean(name = "roleMapper")
    public ModelMapper roleMapper(){
        return new ModelMapper();
    }

    @Bean(name = "providerMapper")
    public ModelMapper providerMapper(){
        return new ModelMapper();
    }

    @Bean(name = "userMapper")
    public ModelMapper userMapper(){
        return new ModelMapper();
    }

    @Bean(name = "saleMapper")
    public ModelMapper saleMapper(){
        //mapper.getConfiguration().setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection));  //Obviamos la conversion del PersistentBag a List
        return new ModelMapper();
    }
}
