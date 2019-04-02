package com.ichop.core.areas.reaction.web.controllers;

import com.ichop.core.areas.reaction.exceptions.CantReactException;
import com.ichop.core.areas.reaction.exceptions.ReactionNotFoundException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ReactionExceptionsExceptionController extends BaseExceptionController {

    @ExceptionHandler(ReactionNotFoundException.class)
    public ModelAndView reactionNotFound(){
        return super.errorPage("The provided reaction cannot be found.");
    }

    @ExceptionHandler(CantReactException.class)
    public ModelAndView cantReact(){
        return super.errorPage("You cannot react on this.");
    }

}
