package itsgosho.web.controllers;

import itsgosho.exceptions.token.TokenCannotBeNullException;
import itsgosho.exceptions.token.TokenNotFoundException;
import itsgosho.exceptions.token.TokenNotValidException;
import itsgosho.exceptions.user.UserCannotBeNullException;
import itsgosho.exceptions.user.UserNotFoundException;
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
}
