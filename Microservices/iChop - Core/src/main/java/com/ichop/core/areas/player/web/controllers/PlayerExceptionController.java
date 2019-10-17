package com.ichop.core.areas.player.web.controllers;

import com.ichop.core.areas.player.exceptions.AccountAlreadyLinkedException;
import com.ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static com.ichop.core.areas.player.constants.PlayerExceptionMessages.ACCOUNT_ALREADY_LINKED;

@ControllerAdvice
public class PlayerExceptionController extends BaseExceptionController {

    @ExceptionHandler(AccountAlreadyLinkedException.class)
    public ModelAndView accountAlreadyLinked(){
        return super.errorPage(ACCOUNT_ALREADY_LINKED);
    }

}
