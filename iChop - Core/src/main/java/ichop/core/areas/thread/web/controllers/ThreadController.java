package ichop.core.areas.thread.web.controllers;

import ichop.core.constants.URLConstants;
import ichop.core.areas.thread.domain.models.view.thread_read.ThreadReadViewModel;
import ichop.core.areas.thread.helpers.view.thread_read.ThreadReadViewHelper;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.base.BaseController;
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
    private final ThreadReadViewHelper threadReadViewHelper;

    @Autowired
    public ThreadController(ThreadServices threadServices, ThreadReadViewHelper threadReadViewHelper) {
        this.threadServices = threadServices;
        this.threadReadViewHelper = threadReadViewHelper;
    }


    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.THREAD_CREATE_GET)
    public ModelAndView getCreateThreadPage() {
        return super.page("thread/thread-create", "Create thread");
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.THREAD_DELETE_POST)
    public String proceedThreadDeletion(@PathVariable String threadId) {
        this.threadServices.delete(threadId);
        return super.redirect("/");
    }

    @GetMapping(URLConstants.THREAD_READ_GET)
    public ModelAndView getThreadReadPage(@PathVariable String threadId, ModelAndView modelAndView) {
        ThreadReadViewModel threadReadViewModel = this.threadReadViewHelper.create(threadId);
        this.threadServices.increaseViews(threadId);
        modelAndView.addObject("thread", threadReadViewModel);
        return super.page("thread/thread-read", threadReadViewModel.getTitle(), modelAndView);
    }
}
