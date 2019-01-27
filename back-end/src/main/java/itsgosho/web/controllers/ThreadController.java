package itsgosho.web.controllers;

import itsgosho.service.thread.ThreadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/thread")
public class ThreadController extends BaseController {

    private final ThreadServices threadServices;

    @Autowired
    public ThreadController(ThreadServices threadServices) {
        this.threadServices = threadServices;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping("/create")
    public ModelAndView createThread(ModelAndView modelAndView) {
        return super.page("base-page","thread/thread-create","Create thread");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public ModelAndView deleteThread(@PathVariable String id) {
        this.threadServices.delete(id);
        return super.redirect("/");
    }
}
