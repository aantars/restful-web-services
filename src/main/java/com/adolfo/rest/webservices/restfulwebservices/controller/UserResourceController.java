package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import com.adolfo.rest.webservices.restfulwebservices.entity.dao.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
