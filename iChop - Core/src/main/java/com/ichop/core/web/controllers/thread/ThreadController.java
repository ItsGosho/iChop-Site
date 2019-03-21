package com.ichop.core.web.controllers.thread;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.view.thread_read.ThreadReadViewModel;
import com.ichop.core.exceptions.thread.ThreadNotFoundException;
import com.ichop.core.service.thread.ThreadServices;
import com.ichop.core.service.thread.view.ThreadViewServices;
import com.ichop.core.web.controllers.BaseController;
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
