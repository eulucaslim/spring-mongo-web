package com.eulucaslim.springmongodb.services;

import com.eulucaslim.springmongodb.domain.User;
import com.eulucaslim.springmongodb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
