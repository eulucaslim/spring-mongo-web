package com.eulucaslim.springmongodb.mapper;

import com.eulucaslim.springmongodb.domain.User;
import com.eulucaslim.springmongodb.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(String id, UserDTO dto){
        return new User(id, dto.getName(), dto.getEmail());
    }

    public UserDTO toDTO(User obj){
        return new UserDTO(obj);
    }
}
