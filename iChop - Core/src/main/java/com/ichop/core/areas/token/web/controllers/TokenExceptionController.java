package com.ichop.core.areas.token.web.controllers;

import com.ichop.core.areas.token.exceptions.TokenNotValidException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TokenExceptionController extends BaseExceptionController {

    @ExceptionHandler(TokenNotValidException.class)
    public ModelAndView tokenNotValid(){
        return super.errorPage("That token is no longer valid.");
    }

}
