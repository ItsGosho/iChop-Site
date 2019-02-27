package ichop.web.controllers.exception;

import ichop.exceptions.token.TokenNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class TokenExceptionController extends ExceptionBaseController {

    @ExceptionHandler(TokenNotValidException.class)
    public ModelAndView userAlreadyExists(){
        return super.errorPage("The provided token is not valid.");
    }

}
