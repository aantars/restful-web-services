package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import com.adolfo.rest.webservices.restfulwebservices.entity.dao.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return userDAOService.findOne(id);
    }

    // save new user
    @PostMapping(path="/users")
    public User createUser(@RequestBody User user){
        return userDAOService.save(user);
    }
}
