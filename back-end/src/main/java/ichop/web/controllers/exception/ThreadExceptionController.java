package ichop.web.controllers.exception;

import ichop.exceptions.thread.ThreadNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ThreadExceptionController extends ExceptionBaseController {

    @ExceptionHandler(ThreadNotFoundException.class)
    public ModelAndView userAlreadyExists(){
        return super.errorPage("The threads you are searching for cannot be found.");
    }

}
