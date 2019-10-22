package com.ichop.core.areas.thread.web.controllers;

import com.ichop.core.areas.thread.exceptions.ThreadNotFoundException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.ichop.core.areas.thread.constants.ThreadExceptionMessages.THREAD_NOT_FOUND;

@ControllerAdvice
public class ThreadExceptionController extends BaseExceptionController {

    @ExceptionHandler(ThreadNotFoundException.class)
    public ModelAndView threadNotFound(){
        return super.errorPage(THREAD_NOT_FOUND);
    }

}
