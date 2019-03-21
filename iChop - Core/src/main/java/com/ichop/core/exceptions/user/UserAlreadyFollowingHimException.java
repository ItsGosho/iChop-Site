package com.ichop.core.exceptions.user;

public class UserAlreadyFollowingHimException extends RuntimeException {

    public UserAlreadyFollowingHimException() {
    }

    public UserAlreadyFollowingHimException(String message) {
        super(message);
    }
}
