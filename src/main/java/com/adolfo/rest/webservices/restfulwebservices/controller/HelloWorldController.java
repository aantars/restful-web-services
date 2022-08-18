package com.adolfo.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // GET /hello-world -- method Hello world
    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hi folks!";
    }

}
