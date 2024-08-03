package com.mitocode.controllers;

import com.mitocode.dto.CategoryDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    @Qualifier("categoryMapper")
    private ModelMapper mapper;

    @Autowired
    private ICategoryService service;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("@authService.checkRole('admin')")
    @GetMapping  //Se debe
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> listDTO = service.readAll()
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);  //Este objeto puede ser nulo
        if(obj == null){
               throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryDTO dto) throws Exception{
        Category obj = service.create(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + dto.getId());
        }
        obj = service.update(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.ACCEPTED);
    }

    //Se puede usar tambien asi
    /*@PutMapping("/{id}")
    public Category update(@PathVariable("id") Integer id ,@RequestBody Category category) throws Exception{
        category.setIdCategory(id);
        return service.update(category);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception{
        if(service.readById(id) == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /////////Queries personalizados///////////

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> readByName(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> list = service.findByName(name)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/find/name/like/{name}")
    public ResponseEntity<List<CategoryDTO>> readByNameLike(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> list = service.findByNameLike(name)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/find/name/contains/{name}")
    public ResponseEntity<List<CategoryDTO>> readByNameContains(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> list = service.findByNameContains(name)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/find/name/starts/{name}")
    public ResponseEntity<List<CategoryDTO>> readByNameStartsWith(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> list = service.findByNameStartsWith(name)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/find/name/ends/{name}")
    public ResponseEntity<List<CategoryDTO>> readByNameEndsWith(@PathVariable("name") String name) throws Exception{
        List<CategoryDTO> list = service.findByNameEndsWith(name)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //Esto es con path variables
    @GetMapping("/find/name/enabled/{name}/{enabled}")
    public ResponseEntity<List<CategoryDTO>> readByNameAndEnabledWithPath(@PathVariable("name") String name, @PathVariable("enabled") boolean enabled) throws Exception{
        List<CategoryDTO> list = service.findByNameAndEnabled(name, enabled)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //Esto es con parametros en la ruta
    @GetMapping("/find/name/enabled")
    public ResponseEntity<List<CategoryDTO>> readByNameAndEnabledWithParams(@RequestParam("name") String name, @RequestParam("status") boolean enabled) throws Exception{
        List<CategoryDTO> list = service.findByNameAndEnabled(name, enabled)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //Esto es con parametros en la ruta
    @GetMapping("/find/name/or/enabled")
    public ResponseEntity<List<CategoryDTO>> readByNameOrEnabledWithParams(@RequestParam("name") String name, @RequestParam("status") boolean enabled) throws Exception{
        List<CategoryDTO> list = service.findByNameOrEnabled(name, enabled)
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/get/name/description")
    public ResponseEntity<List<CategoryDTO>> getColumnsNameDescriptionByNameAndDescription3() throws Exception{
        List<CategoryDTO> list = service.getByNameAndDescription3()
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());  //Este objeto puede ser nulo
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //Paginacion sin Ordenamiento
    @GetMapping("/pagination")
    public ResponseEntity<Page<Category>> findPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) throws Exception{
        PageRequest pageRequest = PageRequest.of(page, size);
        return new ResponseEntity<>(service.findPage(pageRequest), HttpStatus.OK);
    }

    @GetMapping("/order")
    //Para que de acuerdo al parametro que envia el cliente sea el ordenamiento ASC o DESC
    public ResponseEntity<List<CategoryDTO>> findAllOrder(@RequestParam(name = "param", defaultValue = "ASC") String param){
        List<CategoryDTO> list = service.findAllOrder(param)
                .stream()
                .map(category -> mapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
