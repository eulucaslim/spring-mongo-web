package com.eulucaslim.springmongodb.services;

import com.eulucaslim.springmongodb.domain.Post;
import com.eulucaslim.springmongodb.exceptions.ObjectNotFoundException;
import com.eulucaslim.springmongodb.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Post id: " + id + " Not Found"));
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public List<Post> findByTitle(String text) {
        return repository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repository.fullSearch(text, minDate, maxDate);

    }

}
