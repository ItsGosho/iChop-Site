package ichop.core.areas.reaction.web.controllers;

import ichop.core.areas.reaction.exceptions.CantReactException;
import ichop.core.areas.reaction.exceptions.ReactionNotFoundException;
import ichop.core.base.BaseExceptionController;
import ichop.core.areas.reaction.constants.ReactionExceptionsMessages;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ReactionExceptionController extends BaseExceptionController {

    @ExceptionHandler(ReactionNotFoundException.class)
    public ModelAndView reactionNotFound(){
        return super.errorPage(ReactionExceptionsMessages.REACTION_NOT_FOUND);
    }

    @ExceptionHandler(CantReactException.class)
    public ModelAndView cantReact(){
        return super.errorPage(ReactionExceptionsMessages.REACTION_CANT_REACT);
    }

}
