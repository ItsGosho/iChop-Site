package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.domain.models.view.thread.ThreadReadViewModel;
import ichop.exceptions.thread.ThreadException;
import ichop.exceptions.thread.ThreadExceptionMessages;
import ichop.service.thread.CommentServices;
import ichop.service.thread.ThreadServices;
import ichop.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThreadController extends BaseController {

    private final ThreadServices threadServices;
    private final CommentServices commentServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadController(ThreadServices threadServices, CommentServices commentServices, ModelMapper modelMapper) {
        this.threadServices = threadServices;
        this.commentServices = commentServices;
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
        this.threadServices.delete(id);
        return super.redirect("/");
    }

    @GetMapping(URLConstants.THREAD_READ_GET)
    public ModelAndView readThread(@PathVariable String id,ModelAndView modelAndView) {

        ThreadServiceModel threadServiceModel = this.threadServices.getThread(id);

       if(threadServiceModel != null){
           ThreadReadViewModel threadReadViewModel = this.modelMapper.map(threadServiceModel, ThreadReadViewModel.class);
           threadReadViewModel.setTotalViews(threadServiceModel.getViews());
           threadReadViewModel.setTotalComments(threadServiceModel.getComments().size());
           threadReadViewModel.setTotalReactions(threadServiceModel.getReacts().size());
           threadReadViewModel.setCreatorTotalComments(this.commentServices.getTotalCommentsOfUser(this.modelMapper.map(threadServiceModel.getCreator(), UserServiceModel.class)));

           modelAndView.addObject("thread",threadReadViewModel);
           return super.page("base-page","thread/thread-read",threadReadViewModel.getTitle(),modelAndView);
       }

       throw new ThreadException(ThreadExceptionMessages.THREAD_NOT_FOUND);
    }
}
