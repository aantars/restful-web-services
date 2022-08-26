package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import com.adolfo.rest.webservices.restfulwebservices.exception.NoExistingUsersException;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotSavedException;
import com.adolfo.rest.webservices.restfulwebservices.repository.UserRepository;
import com.adolfo.rest.webservices.restfulwebservices.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {
    private UserRepository userRepository;

    public UserJPAController( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // retrieve all users
    @GetMapping(path="/jpa/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // retrieve user
    @GetMapping(path="/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException("id not found: " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        return entityModel;
    }

    // save new user
    @PostMapping(path="/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        }
        catch(Exception e){
            throw new UserNotSavedException("User could not be saved because: " + e.getMessage());
        }

        // "/user/4"
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/jpa/users/{id}")
    public void deleteById(@PathVariable int id){

        userRepository.deleteById(id);
    }
}
