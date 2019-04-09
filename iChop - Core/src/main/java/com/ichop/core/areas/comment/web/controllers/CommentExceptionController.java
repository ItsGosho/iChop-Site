package com.ichop.core.areas.comment.web.controllers;

import com.ichop.core.areas.comment.exceptions.CommentNotFoundException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommentExceptionController extends BaseExceptionController {


    @ExceptionHandler(CommentNotFoundException.class)
    public ModelAndView commentNotFound(){
        return super.errorPage("Comment was not found!");
    }

}
