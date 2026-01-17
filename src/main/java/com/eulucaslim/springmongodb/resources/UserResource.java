package com.eulucaslim.springmongodb.resources;

import com.eulucaslim.springmongodb.domain.Post;
import com.eulucaslim.springmongodb.domain.User;
import com.eulucaslim.springmongodb.dto.UserDTO;
import com.eulucaslim.springmongodb.mapper.UserMapper;
import com.eulucaslim.springmongodb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService service;
    private final UserMapper mapper;

    public UserResource(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO){
        User entity = service.fromDTO(userDTO);
        entity = service.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto) {
        User entity = mapper.toEntity(id, dto);
        User userUpdated = service.update(id, entity);
        return ResponseEntity.ok().body(mapper.toDTO(userUpdated));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}
