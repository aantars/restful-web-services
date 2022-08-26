package com.adolfo.rest.webservices.restfulwebservices.repository;

import com.adolfo.rest.webservices.restfulwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
