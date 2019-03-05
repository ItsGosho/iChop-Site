package ichop.web.controllers.thread;

import com.google.gson.Gson;
import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.CommentCreateBindingModel;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.exceptions.thread.CommentNotFoundException;
import ichop.exceptions.thread.ThreadNotFoundException;
import ichop.service.threads.comment.CommentServices;
import ichop.service.threads.comment.crud.CommentCrudServices;
import ichop.service.threads.thread.crud.ThreadCrudServices;
import ichop.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class CommentController extends BaseController {

    private final CommentCrudServices commentCrudServices;
    private final ModelMapper modelMapper;
    private final ThreadCrudServices threadCrudServices;
    private final CommentServices commentServices;

    @Autowired
    public CommentController(CommentCrudServices commentCrudServices, ModelMapper modelMapper, ThreadCrudServices threadCrudServices, CommentServices commentServices) {
        this.commentCrudServices = commentCrudServices;
        this.modelMapper = modelMapper;
        this.threadCrudServices = threadCrudServices;
        this.commentServices = commentServices;
    }


    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.COMMENT_DELETE_POST)
    public ModelAndView deleteComment(@PathVariable String id) {

        CommentServiceModel commentServiceModel = this.commentCrudServices.getById(id);

        if (commentServiceModel != null) {
            String threadId = commentServiceModel.getThread().getId();

            this.commentCrudServices.delete(commentServiceModel);

            String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}",threadId);
            return super.redirect(redirectUrl);
        }

        throw new CommentNotFoundException();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value= URLConstants.THREAD_CREATE_COMMENT_POST, produces = "application/json")
    @ResponseBody
    public String createComment(@PathVariable String id, CommentCreateBindingModel commentCreateBindingModel, Principal principal) {

        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(id);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        if (threadServiceModel != null) {
            CommentServiceModel commentServiceModel = this.commentServices.addComment(commentCreateBindingModel, userServiceModel, threadServiceModel);

            return new Gson().toJson(true);

        }

        throw new ThreadNotFoundException();
    }

}
