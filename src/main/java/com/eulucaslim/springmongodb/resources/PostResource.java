package com.eulucaslim.springmongodb.resources;

import com.eulucaslim.springmongodb.domain.Post;
import com.eulucaslim.springmongodb.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    private final PostService service;

    public PostResource(PostService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
