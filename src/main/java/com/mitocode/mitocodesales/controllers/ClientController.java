package com.mitocode.mitocodesales.controllers;

import com.mitocode.mitocodesales.dto.ClientDTO;
import com.mitocode.mitocodesales.exception.ModelNotFoundException;
import com.mitocode.mitocodesales.model.Client;
import com.mitocode.mitocodesales.service.IClientService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    @Qualifier("clientMapper")
    private ModelMapper mapper;

    @Autowired
    private IClientService service;

    @GetMapping  //Se debe
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception{
        List<ClientDTO> listDTO = service.readAll()
                .stream()
                .map(cli -> mapper.map(cli, ClientDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);  //Este objeto puede ser nulo
        if(obj == null){
               throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody @Valid ClientDTO dto) throws Exception{
        Client obj = service.create(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto) throws Exception{
        Client obj = service.readById(dto.getIdClient());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + dto.getIdClient());
        }
        obj = service.update(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.ACCEPTED);
    }

    //Se puede usar tambien asi
    /*@PutMapping("/{id}")
    public Client update(@PathVariable("id") Integer id ,@RequestBody Client client) throws Exception{
        client.setIdClient(id);
        return service.update(client);
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
