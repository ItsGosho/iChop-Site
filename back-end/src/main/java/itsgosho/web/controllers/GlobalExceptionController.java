package itsgosho.web.controllers;

import itsgosho.exceptions.thread.ThreadCannotBeNullException;
import itsgosho.exceptions.thread.ThreadNotFoundException;
import itsgosho.exceptions.token.TokenCannotBeNullException;
import itsgosho.exceptions.token.TokenNotFoundException;
import itsgosho.exceptions.token.TokenNotValidException;
import itsgosho.exceptions.user.UserCannotBeNullException;
import itsgosho.exceptions.user.UserNotFoundException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionController extends BaseController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView notExistingPage(){
        return super.viewWithMessage("base-page","notification/error","The page doesnt exist!");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView noMethodSupported(){
        return super.viewWithMessage("base-page","notification/error","This page requires another method to be accessed!");
    }




    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFound(){
        return super.viewWithMessage("base-page","notification/error","User not found!");
    }

    @ExceptionHandler(UserCannotBeNullException.class)
    public ModelAndView userCannotBeNull(){
        return super.viewWithMessage("base-page","notification/error","User cannot be null!");
    }

    @ExceptionHandler(TokenCannotBeNullException.class)
    public ModelAndView tokenCannotBeNull(){
        return super.viewWithMessage("base-page","notification/error","Token cannot be null!");
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ModelAndView tokenNotFound(){
        return super.viewWithMessage("base-page","notification/error","Token not found!");
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ModelAndView tokenNotValid(){
        return super.viewWithMessage("base-page","notification/error","Token is not valid!");
    }

    @ExceptionHandler(ThreadCannotBeNullException.class)
    public ModelAndView threadCannotBeNull(){
        return super.viewWithMessage("base-page","notification/error","Thread cannot be null!");
    }

    @ExceptionHandler(ThreadNotFoundException.class)
    public ModelAndView threadNotFound(){
        return super.viewWithMessage("base-page","notification/error","Thread not found!");
    }
}
