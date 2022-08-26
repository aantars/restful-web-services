package com.adolfo.rest.webservices.restfulwebservices.repository;

import com.adolfo.rest.webservices.restfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Integer> {
}
