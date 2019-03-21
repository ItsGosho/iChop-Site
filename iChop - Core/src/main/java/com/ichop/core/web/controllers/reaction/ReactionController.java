package com.ichop.core.web.controllers.reaction;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.entities.reaction.ReactionType;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.web.controllers.BaseController;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.service.comment.CommentServices;
import com.ichop.core.service.reaction.CommentReactionServices;
import com.ichop.core.service.reaction.ThreadReactionServices;
import com.ichop.core.service.thread.ThreadServices;
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


    private final ThreadServices threadServices;
    private final CommentServices commentServices;
    private final ThreadReactionServices threadReactionServices;
    private final CommentReactionServices commentReactionServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReactionController(ThreadServices threadServices, CommentServices commentServices, ThreadReactionServices threadReactionServices, CommentReactionServices commentReactionServices, ModelMapper modelMapper) {
        this.threadServices = threadServices;
        this.commentServices = commentServices;
        this.threadReactionServices = threadReactionServices;
        this.commentReactionServices = commentReactionServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REACTION_LIKE_POST)
    public String threadReactionLike(@PathVariable String threadId, Principal principal) {

        return proceedThreadReaction(threadId,principal, ReactionType.LIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REACTION_DISLIKE_POST)
    public String threadReactionDislike(@PathVariable String threadId, Principal principal) {

        return proceedThreadReaction(threadId,principal,ReactionType.DISLIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REACTION_LIKE_POST)
    public String commentReactionLike(@PathVariable String commentId, Principal principal) {

        return proceedCommentReaction(commentId,principal,ReactionType.LIKE);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REACTION_DISLIKE_POST)
    public String commentReactionDislike(@PathVariable String commentId, Principal principal) {

        return proceedCommentReaction(commentId,principal,ReactionType.DISLIKE);
    }

    private String proceedThreadReaction(String threadId,Principal principal,ReactionType reactionType){
        ThreadServiceModel threadServiceModel = this.threadServices.findThreadById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.threadReactionServices.createReaction(threadServiceModel,userServiceModel,reactionType);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", threadServiceModel.getId());
        return super.redirect(redirectUrl);
    }

    private String proceedCommentReaction(String threadId,Principal principal,ReactionType reactionType){
        CommentServiceModel commentServiceModel = this.commentServices.findCommentById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map((User)((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.commentReactionServices.createReaction(commentServiceModel,userServiceModel,reactionType);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", commentServiceModel.getThread().getId());
        return super.redirect(redirectUrl);
    }

}
