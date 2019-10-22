package com.ichop.core.areas.jms.handlers;

import com.ichop.core.areas.jms.exception.JmsServerIsDownException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class JmsExceptionsHandler extends BaseExceptionController {


    @ExceptionHandler(JmsServerIsDownException.class)
    public ModelAndView jmsServerIsDown(){
        return super.errorPage("Currently the server is down. Please try again later.");
    }

}
