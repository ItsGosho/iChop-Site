package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.domain.models.view.thread.ThreadReadViewModel;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.service.thread.ThreadServices;
import ichop.service.thread.crud.ThreadCrudServices;
import ichop.service.thread.view.ThreadViewServices;
import ichop.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThreadController extends BaseController {

    private final ThreadServices threadServices;
    private final ThreadCrudServices threadCrudServices;
    private final ThreadViewServices threadViewServices;

    @Autowired
    public ThreadController(ThreadServices threadServices, ThreadCrudServices threadCrudServices, ThreadViewServices threadViewServices) {
        this.threadServices = threadServices;
        this.threadCrudServices = threadCrudServices;
        this.threadViewServices = threadViewServices;
    }


    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.THREAD_CREATE_GET)
    public ModelAndView createThread() {
        return super.page("base-page","thread/thread-create","Create thread");
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.THREAD_DELETE_POST)
    public String deleteThread(@PathVariable String threadId) {
        this.threadCrudServices.delete(threadId);
        return super.redirect("/");
    }

    @GetMapping(URLConstants.THREAD_READ_GET)
    public ModelAndView readThread(@PathVariable String threadId,ModelAndView modelAndView) {

       if(threadId != null && this.threadCrudServices.exists(threadId)){

           this.threadCrudServices.increaseViews(threadId);
           ThreadReadViewModel threadReadViewModel = this.threadViewServices.getThread(threadId);

           modelAndView.addObject("thread",threadReadViewModel);
           return super.page("base-page","thread/thread-read",threadReadViewModel.getTitle(),modelAndView);
       }

       throw new ThreadNotFoundException();
    }
}
