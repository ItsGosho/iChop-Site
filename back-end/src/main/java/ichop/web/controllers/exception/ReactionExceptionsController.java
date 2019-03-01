package ichop.web.controllers.exception;

import ichop.exceptions.thread.ReactionNotFoundException;
import ichop.exceptions.thread.ThreadNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ReactionExceptionsController extends ExceptionBaseController {

    @ExceptionHandler(ReactionNotFoundException.class)
    public ModelAndView reactionNotFound(){
        return super.errorPage("The provided reaction cannot be found.");
    }

}
