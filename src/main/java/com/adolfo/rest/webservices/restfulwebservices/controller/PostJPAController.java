package com.adolfo.rest.webservices.restfulwebservices.controller;

import com.adolfo.rest.webservices.restfulwebservices.entity.Post;
import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.adolfo.rest.webservices.restfulwebservices.exception.UserNotSavedException;
import com.adolfo.rest.webservices.restfulwebservices.repository.PostRepository;
import com.adolfo.rest.webservices.restfulwebservices.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostJPAController {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostJPAController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
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

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> insertPostForUser(@RequestBody Post post, @PathVariable int id){
        Optional<User> user = Optional.of(userRepository.getById(id));
        if (!user.isPresent()){
            throw new UserNotFoundException("id not found: " + id);
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        // "/user/4"
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
