package ichop.web.controllers.thread;

import ichop.constants.URLConstants;
import ichop.domain.entities.threads.reaction.ReactionType;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.threads.comment.CommentServiceModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.threads.reaction.ReactionServices;
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
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping(URLConstants.THREAD_REACTION_LIKE)
    public ModelAndView threadReactionLike(@PathVariable String id, Principal principal) {

        return proceedThreadReaction(id,principal,ReactionType.LIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.THREAD_REACTION_DISLIKE)
    public ModelAndView threadReactionDislike(@PathVariable String id, Principal principal) {

        return proceedThreadReaction(id,principal,ReactionType.DISLIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.COMMENT_REACTION_LIKE)
    public ModelAndView commentReactionLike(@PathVariable String id, Principal principal) {

        return proceedCommentReaction(id,principal,ReactionType.LIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.COMMENT_REACTION_DISLIKE)
    public ModelAndView commentReactionDislike(@PathVariable String id, Principal principal) {

        return proceedCommentReaction(id,principal,ReactionType.DISLIKE);
    }

    private ModelAndView proceedThreadReaction(String threadId,Principal principal,ReactionType reactionType){
        ThreadServiceModel threadServiceModel = this.threadCrudServices.getThread(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reactionServices.addReaction(threadServiceModel,userServiceModel,reactionType);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}", threadServiceModel.getId());
        return super.redirect(redirectUrl);
    }

    private ModelAndView proceedCommentReaction(String threadId,Principal principal,ReactionType reactionType){
        CommentServiceModel commentServiceModel = this.commentCrudServices.getById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map((User)((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.reactionServices.addReaction(commentServiceModel,userServiceModel,reactionType);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{id}", commentServiceModel.getThread().getId());
        return super.redirect(redirectUrl);
    }

}