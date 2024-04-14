package com.mitocode.mitocodesales.controllers;

import com.mitocode.mitocodesales.dto.ProductDTO;
import com.mitocode.mitocodesales.exception.ModelNotFoundException;
import com.mitocode.mitocodesales.model.Product;
import com.mitocode.mitocodesales.service.IProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("productMapper")
    private ModelMapper mapper;

    @Autowired
    private IProductService service;

    @GetMapping  //Se debe
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO> listDTO = service.readAll()
                .stream()
                .map(pro -> mapper.map(pro, ProductDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);  //Este objeto puede ser nulo
        if(obj == null){
               throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO dto) throws Exception{
        Product obj = service.create(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.readById(dto.getIdProduct());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + dto.getIdProduct());
        }
        obj = service.update(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.ACCEPTED);
    }

    //Se puede usar tambien asi
    /*@PutMapping("/{id}")
    public Product update(@PathVariable("id") Integer id ,@RequestBody Product product) throws Exception{
        product.setIdProduct(id);
        return service.update(product);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        if(service.readById(id) == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
