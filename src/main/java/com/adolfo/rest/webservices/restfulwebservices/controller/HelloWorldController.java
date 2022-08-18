package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // GET /hello-world -- method Hello world
    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hi folks!";
    }

    @GetMapping(path = "/hello-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hi Bean!");
    }

    @GetMapping(path="/hello-bean/variable/{name}")
    public HelloWorldBean helloWorldBeanWithVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hi, %s", name));
    }

}
