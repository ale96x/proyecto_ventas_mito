package com.mitocode.controller;

import com.mitocode.controllers.CategoryController;
import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @MockBean
    private ICategoryService service;  //Simulacion de la instancia de CategoryService

    @MockBean(name = "categoryMapper")
    private ModelMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    //Con estos datos simulamos la base de datos
    Category CATEGORY_1 = new Category(1, "TV", "Televisor", true);
    Category CATEGORY_2 = new Category(2, "PSP", "Play Station", true);
    Category CATEGORY_3 = new Category(3, "BOOKS", "Some Books", true);

    CategoryDTO CATEGORYDTO_1 = new CategoryDTO(1, "DVD", "Disc Player", true);
    CategoryDTO CATEGORYDTO_2 = new CategoryDTO(2, "PSP", "Play Station Portatil", true);
    CategoryDTO CATEGORYDTO_3 = new CategoryDTO(3, "BOOKS", "Some Books", true);


    @Test
    public void  readAllTest() throws Exception{   //Simulacion de la peticion
        //Se deben simular todas las acciones posibles
        List<Category> categories = Arrays.asList(CATEGORY_1, CATEGORY_2, CATEGORY_3);  //Creamos una lista de Category

        Mockito.when(service.readAll()).thenReturn(categories);
        Mockito.when(mapper.map(CATEGORY_1, CategoryDTO.class)).thenReturn(CATEGORYDTO_1);

        mockMvc.perform(MockMvcRequestBuilders   //Se usa la variable inyectada de mockMvc, se construye una peticion Mock
                .get("/categories")    //Se indica la url a probar
                .content(MediaType.APPLICATION_JSON_VALUE))     //Se indica el tipo de respuesta que es json
                .andExpect(MockMvcResultMatchers.status().isOk())  //Se indica el status de respuesta esperada para contrastar
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))     //Se indica que la respuesta debe tener 3 resultados
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameCategory", Matchers.is("PSP")));
    }
}
