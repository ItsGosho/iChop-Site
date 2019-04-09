package com.ichop.core.areas.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserPasswordNotValidException extends RuntimeException {

    public UserPasswordNotValidException() {
    }

    public UserPasswordNotValidException(String message) {
        super(message);
    }
}
