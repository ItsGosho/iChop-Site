package com.ichop.core.areas.role.web.controllers;

import com.ichop.core.areas.role.exceptions.RoleNotFoundException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.ichop.core.areas.role.constants.RoleExceptionMessages.ROLE_NOT_FOUND;

@ControllerAdvice
public class RoleExceptionController extends BaseExceptionController {

    @ExceptionHandler(RoleNotFoundException.class)
    public ModelAndView roleNotFound(){
        return super.errorPage(ROLE_NOT_FOUND);
    }

}
