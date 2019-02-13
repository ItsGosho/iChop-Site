package ichop.web.controllers;

import ichop.exceptions.thread.ThreadException;
import ichop.exceptions.token.TokenException;
import ichop.exceptions.user.UserException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView missingrequestParameter(){
        return super.viewWithMessage("base-page","notification/error","Whoops a parameter from the url is missing...");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView noMethodSupported(){
        return super.viewWithMessage("base-page","notification/error","This page requires another method to be accessed!");
    }

    //---

    @ExceptionHandler(UserException.class)
    public ModelAndView userException(Exception ex){
        return super.viewWithMessage("base-page","notification/error",ex.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    public ModelAndView tokenException(Exception ex){
        return super.viewWithMessage("base-page","notification/error",ex.getMessage());
    }

    @ExceptionHandler(ThreadException.class)
    public ModelAndView threadException(Exception ex){
        return super.viewWithMessage("base-page","notification/error",ex.getMessage());
    }

}
