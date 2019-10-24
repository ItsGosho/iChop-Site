package ichop.core.areas.token.web.controllers;

import ichop.core.areas.token.exceptions.TokenNotFoundException;
import ichop.core.areas.token.exceptions.TokenNotValidException;
import ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static ichop.core.areas.token.constants.TokenExceptionMessages.TOKEN_NOT_FOUND;
import static ichop.core.areas.token.constants.TokenExceptionMessages.TOKEN_NOT_VALID;

@ControllerAdvice
public class TokenExceptionController extends BaseExceptionController {

    @ExceptionHandler(TokenNotValidException.class)
    public ModelAndView tokenNotValid(){
        return super.errorPage(TOKEN_NOT_VALID);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ModelAndView tokenNotFound(){
        return super.errorPage(TOKEN_NOT_FOUND);
    }

}
