package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import com.adolfo.rest.webservices.restfulwebservices.entity.dao.UserDAOService;
import com.adolfo.rest.webservices.restfulwebservices.exception.NoExistingUsersException;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResourceController {

    @Autowired
    private UserDAOService userDAOService;

    // retrieve all users
    @GetMapping(path="/users")
    public List<User> getAllUsers(){
        List<User> usersList= userDAOService.findAll();
        if(usersList.isEmpty()){
            throw new NoExistingUsersException("No users found");
        }
        return usersList;
    }

    // retrieve user
    @GetMapping(path="/users/{id}")
    public User getUser(@PathVariable int id){
        User user = userDAOService.findOne(id);
        if (user==null){
            throw new UserNotFoundException("id not found: " + id);
        }
        return user;
    }

    // save new user
    @PostMapping(path="/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser;
        try {
            savedUser = userDAOService.save(user);
        }
        catch(Exception e){
            throw new UserNotSavedException("User could not be saved because: " + e.getMessage());
        }

        // "/user/4"
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/users/{id}")
    public Optional<User> deleteById(@PathVariable int id){

        return userDAOService.deleteUserByID(id);
    }
}
