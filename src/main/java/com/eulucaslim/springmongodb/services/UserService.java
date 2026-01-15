package com.eulucaslim.springmongodb.services;

import com.eulucaslim.springmongodb.domain.User;
import com.eulucaslim.springmongodb.dto.UserDTO;
import com.eulucaslim.springmongodb.exceptions.ObjectNotFoundException;
import com.eulucaslim.springmongodb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User id: " + id + " Not Found"));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(String id, User obj) {
        User user = findById(id);
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        return repository.save(user);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
