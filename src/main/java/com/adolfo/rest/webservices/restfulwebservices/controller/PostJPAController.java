package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.Post;
import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.adolfo.rest.webservices.restfulwebservices.repository.PostRepository;
import com.adolfo.rest.webservices.restfulwebservices.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PostJPAController {

    private PostRepository repository;
    private UserRepository userRepository;

    public PostJPAController(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable int id){
        Optional<User> user = Optional.of(userRepository.getById(id));
        if (!user.isPresent()){
            throw new UserNotFoundException("id not found: " + id);
        }
        return user.get().getPosts();
    }

}
