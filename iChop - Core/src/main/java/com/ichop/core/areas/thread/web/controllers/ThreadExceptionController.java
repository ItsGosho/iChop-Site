package com.ichop.core.areas.thread.web.controllers;

import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ThreadExceptionController extends BaseExceptionController {

    @ExceptionHandler(ThreadNotFoundException.class)
    public ModelAndView threadNotFound(){
        return super.errorPage("The threads you are searching for cannot be found.");
    }

}
