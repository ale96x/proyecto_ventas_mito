package com.mitocode.mitocodesales.controllers;

import com.mitocode.mitocodesales.dto.UserDTO;
import com.mitocode.mitocodesales.exception.ModelNotFoundException;
import com.mitocode.mitocodesales.model.User;
import com.mitocode.mitocodesales.service.IUserService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("userMapper")
    private ModelMapper mapper;

    @Autowired
    private IUserService service;

    @GetMapping  //Se debe
    public ResponseEntity<List<UserDTO>> readAll() throws Exception{
        List<UserDTO> listDTO = service.readAll()
                .stream()
                .map(u -> mapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);  //Este objeto puede ser nulo
        if(obj == null){
               throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO dto) throws Exception{
        User obj = service.create(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.readById(dto.getIdUser());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + dto.getIdUser());
        }
        obj = service.update(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.ACCEPTED);
    }

    //Se puede usar tambien asi
    /*@PutMapping("/{id}")
    public User update(@PathVariable("id") Integer id ,@RequestBody User user) throws Exception{
        user.setIdUser(id);
        return service.update(user);
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
