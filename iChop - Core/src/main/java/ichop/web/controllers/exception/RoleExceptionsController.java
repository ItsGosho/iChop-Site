package ichop.web.controllers.exception;

import ichop.exceptions.role.UserRoleNotFoundException;
import ichop.exceptions.user.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class RoleExceptionsController extends ExceptionBaseController {

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ModelAndView userRoleNotFound(){
        return super.errorPage("The provided user role is not found or not accessible.");
    }

}
