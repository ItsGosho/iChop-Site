package ichop.web.controllers.exception;

import ichop.web.controllers.exception.ExceptionBaseController;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionController extends ExceptionBaseController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView notExistingPage(){
        return super.errorPage("The page doesnt exist!");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView noMethodSupported(){
        return super.errorPage("This page requires another method to be accessed!");
    }

}
