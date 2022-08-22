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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity createUser(@RequestBody User user){
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

    // Retrieve all posts for a User GET /users/{id}/posts
    // Create posts for a User - POST /users/{id}/posts
    // Retrieve details of a post GET /users/{id}/posts/{post_id}

}
