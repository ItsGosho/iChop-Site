package com.ichop.core.areas.thread.exceptions;

public class ThreadNotFoundException extends RuntimeException {

    public ThreadNotFoundException() {
    }

    public ThreadNotFoundException(String message) {
        super(message);
    }
}
