package com.ichop.core.areas.reaction.web.controllers;

import com.ichop.core.areas.reaction.exceptions.CantReactException;
import com.ichop.core.areas.reaction.exceptions.ReactionNotFoundException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.ichop.core.areas.reaction.constants.ReactionExceptionsMessages.REACTION_CANT_REACT;
import static com.ichop.core.areas.reaction.constants.ReactionExceptionsMessages.REACTION_NOT_FOUND;

@ControllerAdvice
public class ReactionExceptionController extends BaseExceptionController {

    @ExceptionHandler(ReactionNotFoundException.class)
    public ModelAndView reactionNotFound(){
        return super.errorPage(REACTION_NOT_FOUND);
    }

    @ExceptionHandler(CantReactException.class)
    public ModelAndView cantReact(){
        return super.errorPage(REACTION_CANT_REACT);
    }

}
