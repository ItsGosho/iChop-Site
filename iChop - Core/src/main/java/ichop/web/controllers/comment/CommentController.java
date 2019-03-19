package ichop.web.controllers.comment;

import com.google.gson.Gson;
import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.comment.CommentCreateBindingModel;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.comment.CommentServices;
import ichop.service.thread.ThreadServices;
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

    private final CommentServices commentServices;
    private final ModelMapper modelMapper;
    private final ThreadServices threadServices;

    @Autowired
    public CommentController(CommentServices commentServices, ModelMapper modelMapper, ThreadServices threadServices) {
        this.commentServices = commentServices;
        this.modelMapper = modelMapper;
        this.threadServices = threadServices;
    }


    @PreAuthorize("hasAuthority('MODERATOR') or @commentCrudServicesImp.getById(#commentId).creator.username.equals(principal.username)")
    @PostMapping(URLConstants.COMMENT_DELETE_POST)
    public String deleteComment(@PathVariable String commentId) {

        CommentServiceModel commentServiceModel = this.commentServices.findCommentById(commentId);

        this.commentServices.deleteComment(commentId);
        String threadId = commentServiceModel.getThread().getId();

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", threadId);
        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = URLConstants.THREAD_CREATE_COMMENT_POST, produces = "application/json")
    @ResponseBody
    public String createComment(@PathVariable String threadId, CommentCreateBindingModel commentCreateBindingModel, Principal principal) {

        ThreadServiceModel threadServiceModel = this.threadServices.findThreadById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        CommentServiceModel commentServiceModel = this.commentServices.createComment(commentCreateBindingModel, userServiceModel, threadServiceModel);

        return new Gson().toJson(true);
    }

}
