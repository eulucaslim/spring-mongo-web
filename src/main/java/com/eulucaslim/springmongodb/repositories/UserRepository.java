package com.eulucaslim.springmongodb.repositories;

import com.eulucaslim.springmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
