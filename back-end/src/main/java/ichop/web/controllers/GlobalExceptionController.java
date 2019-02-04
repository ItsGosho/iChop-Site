package ichop.web.controllers;

import ichop.exceptions.thread.ThreadCannotBeNullException;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.exceptions.token.TokenCannotBeNullException;
import ichop.exceptions.token.TokenNotFoundException;
import ichop.exceptions.token.TokenNotValidException;
import ichop.exceptions.user.PasswordsDoesntMatchException;
import ichop.exceptions.user.UserAlreadyExistsException;
import ichop.exceptions.user.UserCannotBeNullException;
import ichop.exceptions.user.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler({UserNotFoundException.class, UsernameNotFoundException.class})
    public ModelAndView userNotFound(){
        return super.viewWithMessage("base-page","notification/error","User not found!");
    }

    @ExceptionHandler(PasswordsDoesntMatchException.class)
    public ModelAndView passwordDoesntMatch(){
        return super.viewWithMessage("base-page","notification/error","Passwords doesnt match!");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView userAlreadyExists(){
        return super.viewWithMessage("base-page","notification/error","User already exists!");
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
