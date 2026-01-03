package com.eulucaslim.springmongodb.resources;

import com.eulucaslim.springmongodb.domain.User;
import com.eulucaslim.springmongodb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
