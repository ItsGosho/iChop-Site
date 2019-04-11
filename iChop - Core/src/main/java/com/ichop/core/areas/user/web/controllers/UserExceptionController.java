package com.ichop.core.areas.user.web.controllers;

import com.ichop.core.areas.user.exceptions.*;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.ichop.core.areas.user.constants.UserExceptionMessages.*;

@ControllerAdvice
public class UserExceptionController extends BaseExceptionController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView userAlreadyExists(){
        return super.errorPage(USER_ALREADY_EXISTS);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFound(){
        return super.errorPage(USER_NOT_FOUND);
    }

    @ExceptionHandler(UserPasswordNotValidException.class)
    public ModelAndView userPasswordsDoesntMatch(){
        return super.errorPage(USER_PASSWORD_NOT_VALID);
    }

    @ExceptionHandler(UserAlreadyReacted.class)
    public ModelAndView userAlreadyLikedThis(){
        return super.errorPage(USER_ALREADY_REACTED);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ModelAndView userNoAuthorized(){
        return super.errorPage(USER_NOT_AUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyFollowingHimException.class)
    public ModelAndView userAlreadyFollowed(){
        return super.errorPage(USER_ALREADY_FOLLOWING_HIM);
    }

    @ExceptionHandler(UserNotFollowingHimException.class)
    public ModelAndView userNotFollowingHim(){
        return super.errorPage(USER_NOT_FOLLOWING_HIM);
    }

    @ExceptionHandler(UserCannotFollowException.class)
    public ModelAndView userCannotFollow(){
        return super.errorPage(USER_CANT_FOLLOW);
    }

}
