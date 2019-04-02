package com.ichop.core.areas.user.exceptions;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException() {
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
