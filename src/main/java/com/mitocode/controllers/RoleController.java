package com.mitocode.controllers;

import com.mitocode.dto.RoleDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Role;
import com.mitocode.service.IRoleService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    @Qualifier("roleMapper")
    private ModelMapper mapper;

    @Autowired
    private IRoleService service;

    @GetMapping  //Se debe
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception{
        List<RoleDTO> listDTO = service.readAll()
                .stream()
                .map(rol -> mapper.map(rol, RoleDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Role obj = service.readById(id);  //Este objeto puede ser nulo
        if(obj == null){
               throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody @Valid RoleDTO dto) throws Exception{
        Role obj = service.create(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto) throws Exception{
        Role obj = service.readById(dto.getIdRole());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + dto.getIdRole());
        }
        obj = service.update(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class), HttpStatus.ACCEPTED);
    }

    //Se puede usar tambien asi
    /*@PutMapping("/{id}")
    public Role update(@PathVariable("id") Integer id ,@RequestBody Role role) throws Exception{
        role.setIdRole(id);
        return service.update(role);
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
