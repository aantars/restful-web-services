package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import com.adolfo.rest.webservices.restfulwebservices.entity.dao.UserDAOService;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResourceController {

    @Autowired
    private UserDAOService userDAOService;

    // retrieve all users

    @GetMapping(path="/users")
    public List<User> getAllUsers(){
        return userDAOService.findAll();
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
    public ResponseEntity createUser(@RequestBody User user){
       User savedUser = userDAOService.save(user);
        // "/user/4"
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
