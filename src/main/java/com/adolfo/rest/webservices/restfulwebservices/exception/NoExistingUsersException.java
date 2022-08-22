package com.adolfo.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoExistingUsersException extends RuntimeException {
    public NoExistingUsersException(String s) {
        super(s);
    }
}
