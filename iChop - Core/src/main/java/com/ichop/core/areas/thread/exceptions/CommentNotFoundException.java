package com.ichop.core.areas.thread.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
