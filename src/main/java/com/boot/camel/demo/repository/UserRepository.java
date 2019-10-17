package com.boot.camel.demo.repository;

import java.util.List;

import com.boot.camel.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public List<User> findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);
    public List<User> findAll();

}
