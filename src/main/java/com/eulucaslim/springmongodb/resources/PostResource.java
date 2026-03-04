package com.eulucaslim.springmongodb.resources;

import com.eulucaslim.springmongodb.domain.Post;
import com.eulucaslim.springmongodb.resources.util.URL;
import com.eulucaslim.springmongodb.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> obj = service.findByTitle(text);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        List<Post> posts = service.findAll();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate ) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> posts = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(posts);
    }

}
