package com.ichop.core.areas.user.web.controllers;

import com.ichop.core.areas.user.exceptions.*;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserExceptionsExceptionController extends BaseExceptionController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView userAlreadyExists(){
        return super.errorPage("The provided user already isThreadExistsById.");
    }

    @ExceptionHandler(UserCannotBeNullException.class)
    public ModelAndView userCannotBeNull(){
        return super.errorPage("The provided user cannot be null.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFound(){
        return super.errorPage("The provided user wasnt found!");
    }

    @ExceptionHandler(UserPasswordNotValidException.class)
    public ModelAndView userPasswordsDoesntMatch(){
        return super.errorPage("The provided passwords doesnt match!");
    }

    @ExceptionHandler(UserAlreadyReacted.class)
    public ModelAndView userAlreadyLikedThis(){
        return super.errorPage("You have already casted your vote on this.");
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ModelAndView userNoAuthorized(){
        return super.errorPage("You cant access this page!");
    }

    @ExceptionHandler(UserAlreadyFollowingHimException.class)
    public ModelAndView userAlreadyFollowed(){
        return super.errorPage("You already followed this user.");
    }

    @ExceptionHandler(UserNotFollowingHimException.class)
    public ModelAndView userNotFollowingHim(){
        return super.errorPage("You are not following this user.");
    }

    @ExceptionHandler(UserCannotFollowException.class)
    public ModelAndView userCannotFollow(){
        return super.errorPage("You cannot follow this user.");
    }

}
