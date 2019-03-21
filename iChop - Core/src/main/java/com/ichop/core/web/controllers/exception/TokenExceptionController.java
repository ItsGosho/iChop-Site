package com.ichop.core.web.controllers.exception;

import com.ichop.core.exceptions.token.TokenNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TokenExceptionController extends ExceptionBaseController {

    @ExceptionHandler(TokenNotValidException.class)
    public ModelAndView userAlreadyExists(){
        return super.errorPage("That token is no longer valid.");
    }

}
