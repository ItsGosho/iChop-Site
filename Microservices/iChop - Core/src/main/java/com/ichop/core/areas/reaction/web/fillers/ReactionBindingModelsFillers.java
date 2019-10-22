package com.ichop.core.areas.reaction.web.fillers;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.reaction.domain.entities.ReactionType;
import com.ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import com.ichop.core.areas.reaction.domain.models.binding.ThreadReactionCreateBindingModel;
import com.ichop.core.areas.reaction.web.controllers.ReactionController;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.validators.aspects.SkipOnNull;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@ControllerAdvice(assignableTypes = ReactionController.class)
public class ReactionBindingModelsFillers {

    private final UserServices userServices;
    private final ThreadServices threadServices;
    private final CommentServices commentServices;

    @Autowired
    public ReactionBindingModelsFillers(UserServices userServices, ThreadServices threadServices, CommentServices commentServices) {
        this.userServices = userServices;
        this.threadServices = threadServices;
        this.commentServices = commentServices;
    }

    @SkipOnNull
    @ModelAttribute
    public ThreadReactionCreateBindingModel fill(ThreadReactionCreateBindingModel bindingModel,
                                                 @PathVariable(required = false) String threadId,
                                                 @PathVariable(required = false) String reaction,
                                                 Principal principal) {
        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());
        ThreadServiceModel thread = this.threadServices.findById(threadId);
        ReactionType reactionType = EnumUtils.isValidEnum(ReactionType.class, reaction.toUpperCase())  ? ReactionType.valueOf(reaction.toUpperCase()) : null;

        bindingModel.setUser(user);
        bindingModel.setThread(thread);
        bindingModel.setReactionType(reactionType);

        return bindingModel;
    }

    @SkipOnNull
    @ModelAttribute
    public CommentReactionCreateBindingModel fill(CommentReactionCreateBindingModel bindingModel,
                                                  @PathVariable(required = false) String commentId,
                                                  @PathVariable(required = false) String reaction,
                                                  Principal principal) {
        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());
        CommentServiceModel comment = this.commentServices.findById(commentId);
        ReactionType reactionType = EnumUtils.isValidEnum(ReactionType.class, reaction.toUpperCase())  ? ReactionType.valueOf(reaction.toUpperCase()) : null;

        bindingModel.setUser(user);
        bindingModel.setComment(comment);
        bindingModel.setReactionType(reactionType);

        return bindingModel;
    }


}
