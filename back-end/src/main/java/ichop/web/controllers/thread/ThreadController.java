package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.domain.models.view.thread_read.ThreadReadViewModel;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.service.thread.ThreadServices;
import ichop.service.thread.view.ThreadViewServices;
import ichop.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThreadController extends BaseController {

    private final ThreadServices threadServices;
    private final ThreadViewServices threadViewServices;

    @Autowired
    public ThreadController(ThreadServices threadServices, ThreadViewServices threadViewServices) {
        this.threadServices = threadServices;
        this.threadViewServices = threadViewServices;
    }


    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.THREAD_CREATE_GET)
    public ModelAndView createThread() {
        return super.page("thread/thread-createPost","Create thread");
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.THREAD_DELETE_POST)
    public String deleteThread(@PathVariable String threadId) {
        this.threadServices.deleteThreadById(threadId);
        return super.redirect("/");
    }

    @GetMapping(URLConstants.THREAD_READ_GET)
    public ModelAndView readThread(@PathVariable String threadId,ModelAndView modelAndView) {

       if(threadId != null && this.threadServices.isThreadExistsById(threadId)){

           this.threadServices.increaseViews(threadId);
           ThreadReadViewModel threadReadViewModel = this.threadViewServices.getThreadById(threadId);

           modelAndView.addObject("thread",threadReadViewModel);
           return super.page("thread/thread-read",threadReadViewModel.getTitle(),modelAndView);
       }

       throw new ThreadNotFoundException();
    }
}
