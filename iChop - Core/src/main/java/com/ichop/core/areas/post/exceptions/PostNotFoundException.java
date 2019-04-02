package com.ichop.core.areas.post.exceptions;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
