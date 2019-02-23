package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.domain.models.view.thread.ThreadReadViewModel;
import ichop.exceptions.thread.ThreadException;
import ichop.exceptions.thread.ThreadExceptionMessages;
import ichop.service.thread.crud.ThreadCrudServices;
import ichop.service.thread.view.ThreadViewServices;
import ichop.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThreadController extends BaseController {

    private final ThreadCrudServices threadCrudServices;
    private final ThreadViewServices threadViewServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadController(ThreadCrudServices threadCrudServices, ThreadViewServices threadViewServices, ModelMapper modelMapper) {
        this.threadCrudServices = threadCrudServices;
        this.threadViewServices = threadViewServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.THREAD_CREATE_GET)
    public ModelAndView createThread() {
        return super.page("base-page","thread/thread-create","Create thread");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(URLConstants.THREAD_DELETE_GET)
    public ModelAndView deleteThread(@PathVariable String id) {
        this.threadCrudServices.delete(id);
        return super.redirect("/");
    }

    @GetMapping(URLConstants.THREAD_READ_GET)
    public ModelAndView readThread(@PathVariable String id,ModelAndView modelAndView) {

       if(id != null && !this.threadCrudServices.exists(id)){

           ThreadReadViewModel threadReadViewModel = this.threadViewServices.getThread(id);

           modelAndView.addObject("thread",threadReadViewModel);
           return super.page("base-page","thread/thread-read",threadReadViewModel.getTitle(),modelAndView);
       }

       throw new ThreadException(ThreadExceptionMessages.THREAD_NOT_FOUND);
    }
}
