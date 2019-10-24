package ichop.core.areas.comment.web.controllers;

import ichop.core.areas.comment.exceptions.CommentNotFoundException;
import ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static ichop.core.areas.comment.constants.CommentExceptionsMessages.COMMENT_NOT_FOUND;

@ControllerAdvice
public class CommentExceptionController extends BaseExceptionController {


    @ExceptionHandler(CommentNotFoundException.class)
    public ModelAndView commentNotFound(){
        return super.errorPage(COMMENT_NOT_FOUND);
    }

}
