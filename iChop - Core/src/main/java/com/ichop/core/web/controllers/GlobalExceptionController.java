package com.ichop.core.web.controllers;

import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionController extends BaseExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView notExistingPage(){
        return super.errorPage("The page doesnt exist!");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView noMethodSupported(){
        return super.errorPage("This page requires another method to be accessed!");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView noRequiredParameter(){
        return super.errorPage("This page requires additional parameter to be accessed!");
    }
}
