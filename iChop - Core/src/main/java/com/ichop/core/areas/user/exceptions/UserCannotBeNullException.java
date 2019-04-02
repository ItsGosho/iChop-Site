package com.ichop.core.areas.user.exceptions;

public class UserCannotBeNullException extends RuntimeException {

    public UserCannotBeNullException() {
    }

    public UserCannotBeNullException(String message) {
        super(message);
    }
}
