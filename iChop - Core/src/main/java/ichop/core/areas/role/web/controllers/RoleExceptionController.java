package ichop.core.areas.role.web.controllers;

import ichop.core.areas.role.exceptions.RoleNotFoundException;
import ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static ichop.core.areas.role.constants.RoleExceptionMessages.ROLE_NOT_FOUND;

@ControllerAdvice
public class RoleExceptionController extends BaseExceptionController {

    @ExceptionHandler(RoleNotFoundException.class)
    public ModelAndView roleNotFound(){
        return super.errorPage(ROLE_NOT_FOUND);
    }

}
