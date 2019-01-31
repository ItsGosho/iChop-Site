package itsgosho.web.controllers.thread;

import itsgosho.constants.URLConstants;
import itsgosho.service.thread.ThreadServices;
import itsgosho.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(URLConstants.THREAD_PREFIX)
public class ThreadController extends BaseController {

    private final ThreadServices threadServices;

    @Autowired
    public ThreadController(ThreadServices threadServices) {
        this.threadServices = threadServices;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.THREAD_CREATE)
    public ModelAndView createThread() {
        return super.page("base-page","thread/thread-create","Create thread");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(URLConstants.THREAD_DELETE)
    public ModelAndView deleteThread(@PathVariable String id) {
        this.threadServices.delete(id);
        return super.redirect("/");
    }
}
