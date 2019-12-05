package com.ichop.linkaccount.handlers;

import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class JmsErrorHandler implements ErrorHandler {

    /*
    *
    * Handles all expired or not destination exists messages.Without this the queue old messages
    * are never proceed which cause the application to not receive them.
    *
    * */
 
    @Override
    public void handleError(Throwable t) {
    }
 
}