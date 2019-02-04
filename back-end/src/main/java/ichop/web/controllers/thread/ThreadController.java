package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.service.thread.ThreadServices;
import ichop.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThreadController extends BaseController {

    private final ThreadServices threadServices;

    @Autowired
    public ThreadController(ThreadServices threadServices) {
        this.threadServices = threadServices;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.THREAD_CREATE_GET)
    public ModelAndView createThread() {
        return super.page("base-page","thread/thread-create","Create thread");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(URLConstants.THREAD_DELETE_GET)
    public ModelAndView deleteThread(@PathVariable String id) {
        this.threadServices.delete(id);
        return super.redirect("/");
    }
}
