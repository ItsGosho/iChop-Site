package ichop.web.controllers.reaction;

import ichop.constants.URLConstants;
import ichop.domain.entities.reaction.ReactionType;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.reaction.ReactionServices;
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

import java.security.Principal;

@Controller
public class ReactionController extends BaseController {


    private final ThreadCrudServices threadCrudServices;
    private final CommentCrudServices commentCrudServices;
    private final ReactionServices reactionServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReactionController(ThreadCrudServices threadCrudServices, CommentCrudServices commentCrudServices, ReactionServices reactionServices, ModelMapper modelMapper) {
        this.threadCrudServices = threadCrudServices;
        this.commentCrudServices = commentCrudServices;
        this.reactionServices = reactionServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REACTION_LIKE_POST)
    public String threadReactionLike(@PathVariable String id, Principal principal) {

        return proceedThreadReaction(id,principal,ReactionType.LIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REACTION_DISLIKE_POST)
    public String threadReactionDislike(@PathVariable String id, Principal principal) {

        return proceedThreadReaction(id,principal,ReactionType.DISLIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REACTION_LIKE_POST)
    public String commentReactionLike(@PathVariable String id, Principal principal) {

        return proceedCommentReaction(id,principal,ReactionType.LIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REACTION_DISLIKE_POST)
    public String commentReactionDislike(@PathVariable String id, Principal principal) {

        return proceedCommentReaction(id,principal,ReactionType.DISLIKE);
    }

    private String proceedThreadReaction(String threadId,Principal principal,ReactionType reactionType){
        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reactionServices.addReaction(threadServiceModel,userServiceModel,reactionType);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}", threadServiceModel.getId());
        return super.redirect(redirectUrl);
    }

    private String proceedCommentReaction(String threadId,Principal principal,ReactionType reactionType){
        CommentServiceModel commentServiceModel = this.commentCrudServices.getById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map((User)((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reactionServices.addReaction(commentServiceModel,userServiceModel,reactionType);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}", commentServiceModel.getThread().getId());
        return super.redirect(redirectUrl);
    }

}
