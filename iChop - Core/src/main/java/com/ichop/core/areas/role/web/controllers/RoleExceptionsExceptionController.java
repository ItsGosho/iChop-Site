package com.ichop.core.areas.role.web.controllers;

import com.ichop.core.areas.role.exceptions.RoleNotFoundException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class RoleExceptionsExceptionController extends BaseExceptionController {

    @ExceptionHandler(RoleNotFoundException.class)
    public ModelAndView roleNotFound(){
        return super.errorPage("The provided user role is not found or not accessible.");
    }

}
