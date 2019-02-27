package ichop.web.controllers.exception;

import ichop.exceptions.user.UserAlreadyExistsException;
import ichop.exceptions.user.UserCannotBeNullException;
import ichop.exceptions.user.UserNotFoundException;
import ichop.exceptions.user.UserPasswordNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UserExceptionsController extends ExceptionBaseController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView userAlreadyExists(){
        return super.errorPage("The provided user already exists.");
    }

    @ExceptionHandler(UserCannotBeNullException.class)
    public ModelAndView userCannotBeNull(){
        return super.errorPage("The provided user cannot be null.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView userNotFound(){
        return super.errorPage("The provided user wasnt found!");
    }

    @ExceptionHandler(UserPasswordNotValidException.class)
    public ModelAndView userPasswordsDoesntMatch(){
        return super.errorPage("The provided passwords doesnt match!");
    }

}
