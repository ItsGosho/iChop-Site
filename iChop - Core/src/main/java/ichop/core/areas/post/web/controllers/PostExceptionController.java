package ichop.core.areas.post.web.controllers;

import ichop.core.areas.post.exceptions.PostNotFoundException;
import ichop.core.base.BaseExceptionController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static ichop.core.areas.post.constants.PostExceptionConstants.POST_NOT_FOUND;

@ControllerAdvice
public class PostExceptionController extends BaseExceptionController {

    @ExceptionHandler(PostNotFoundException.class)
    public ModelAndView postNotFound(){
        return super.errorPage(POST_NOT_FOUND);
    }

}
