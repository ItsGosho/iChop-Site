package ichop.web.controllers.comment;

import com.google.gson.Gson;
import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.comment.CommentCreateBindingModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.comment.CommentServices;
import ichop.service.comment.crud.CommentCrudServices;
import ichop.service.thread.crud.ThreadCrudServices;
import ichop.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String deleteComment(@PathVariable String id) {

        CommentServiceModel commentServiceModel = this.commentCrudServices.getById(id);

        this.commentCrudServices.delete(id);
        String threadId = commentServiceModel.getThread().getId();

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}", threadId);
        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = URLConstants.THREAD_CREATE_COMMENT_POST, produces = "application/json")
    @ResponseBody
    public String createComment(@PathVariable String id, CommentCreateBindingModel commentCreateBindingModel, Principal principal) {

        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(id);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        CommentServiceModel commentServiceModel = this.commentServices.addComment(commentCreateBindingModel, userServiceModel, threadServiceModel);

        return new Gson().toJson(true);
    }

}
