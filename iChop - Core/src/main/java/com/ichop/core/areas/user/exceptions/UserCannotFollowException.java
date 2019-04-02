package com.ichop.core.areas.user.exceptions;

public class UserCannotFollowException extends RuntimeException {

    public UserCannotFollowException() {
    }

    public UserCannotFollowException(String message) {
        super(message);
    }
}
