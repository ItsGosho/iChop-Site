package ichop.core.areas.player.web.controllers;

import ichop.core.areas.player.exceptions.AccountAlreadyLinkedException;
import ichop.core.base.BaseExceptionController;
import ichop.core.areas.player.constants.PlayerExceptionMessages;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class PlayerExceptionController extends BaseExceptionController {

    @ExceptionHandler(AccountAlreadyLinkedException.class)
    public ModelAndView accountAlreadyLinked(){
        return super.errorPage(PlayerExceptionMessages.ACCOUNT_ALREADY_LINKED);
    }

}
