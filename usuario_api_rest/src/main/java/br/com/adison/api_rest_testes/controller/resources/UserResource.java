package br.com.adison.api_rest_testes.controller.resources;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.model.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserResource {
    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService service;

    @GetMapping(ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<Users> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(objectsUsersDaLista -> mapper
                .map(objectsUsersDaLista, UserDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO object){
        Users newObject = service.create(object);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ID)
                .buildAndExpand(newObject.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO object){
        object.setId(id);
        Users newObject = service.update(object);
        return ResponseEntity.ok().body(mapper.map(newObject, UserDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
