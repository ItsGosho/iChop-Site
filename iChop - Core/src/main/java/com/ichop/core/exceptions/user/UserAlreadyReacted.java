package com.ichop.core.exceptions.user;

public class UserAlreadyReacted extends RuntimeException {

    public UserAlreadyReacted() {
    }

    public UserAlreadyReacted(String message) {
        super(message);
    }
}
