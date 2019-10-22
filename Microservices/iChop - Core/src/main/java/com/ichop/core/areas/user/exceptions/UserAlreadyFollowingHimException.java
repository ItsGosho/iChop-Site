package com.ichop.core.areas.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyFollowingHimException extends RuntimeException {

    public UserAlreadyFollowingHimException() {
    }

    public UserAlreadyFollowingHimException(String message) {
        super(message);
    }
}
