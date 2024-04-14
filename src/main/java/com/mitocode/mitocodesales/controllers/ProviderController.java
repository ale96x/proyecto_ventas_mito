package com.mitocode.mitocodesales.controllers;

import com.mitocode.mitocodesales.dto.ProviderDTO;
import com.mitocode.mitocodesales.exception.ModelNotFoundException;
import com.mitocode.mitocodesales.model.Provider;
import com.mitocode.mitocodesales.service.IProviderService;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    @Qualifier("categoryMapper")
    private ModelMapper mapper;

    @Autowired
    private IProviderService service;

    @GetMapping  //Se debe
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{
        List<ProviderDTO> listDTO = service.readAll()
                .stream()
                .map(pro -> mapper.map(pro, ProviderDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Provider obj = service.readById(id);  //Este objeto puede ser nulo
        if(obj == null){
               throw new ModelNotFoundException("ID NOT FOUND:" + id);
        }
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@RequestBody @Valid ProviderDTO dto) throws Exception{
        Provider obj = service.create(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.readById(dto.getIdProvider());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND:" + dto.getIdProvider());
        }
        obj = service.update(mapper.map(dto, Provider.class));
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class), HttpStatus.ACCEPTED);
    }

    //Se puede usar tambien asi
    /*@PutMapping("/{id}")
    public Provider update(@PathVariable("id") Integer id ,@RequestBody Provider provider) throws Exception{
        provider.setIdProvider(id);
        return service.update(provider);
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
