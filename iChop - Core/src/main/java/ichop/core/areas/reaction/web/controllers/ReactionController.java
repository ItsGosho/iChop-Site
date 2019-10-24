package ichop.core.areas.reaction.web.controllers;

import ichop.core.areas.reaction.domain.models.binding.CommentReactionCreateBindingModel;
import ichop.core.areas.reaction.domain.models.binding.ThreadReactionCreateBindingModel;
import ichop.core.areas.reaction.services.CommentReactionServices;
import ichop.core.areas.reaction.services.ThreadReactionServices;
import ichop.core.base.BaseController;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ReactionController extends BaseController {

    private final ThreadReactionServices threadReactionServices;
    private final CommentReactionServices commentReactionServices;

    @Autowired
    public ReactionController(ThreadReactionServices threadReactionServices, CommentReactionServices commentReactionServices) {
        this.threadReactionServices = threadReactionServices;
        this.commentReactionServices = commentReactionServices;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REACTION_DISLIKE_POST)
    public String proceedThreadDislikedReaction(@PathVariable String threadId, @PathVariable String reaction,
                                                @Valid @ModelAttribute ThreadReactionCreateBindingModel bindingModel,
                                                BindingResult bindingResult) {

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", bindingModel.getThread().getId());

        if (bindingResult.hasErrors()) {
            return super.redirect(redirectUrl);
        }

        this.threadReactionServices.create(bindingModel);

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REACTION_DISLIKE_POST)
    public String proceedCommentDislikeReaction(@PathVariable String commentId, @PathVariable String reaction,
                                                @Valid @ModelAttribute CommentReactionCreateBindingModel bindingModel,
                                                BindingResult bindingResult) {

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", bindingModel.getComment().getThread().getId());

        if (bindingResult.hasErrors()) {
            return super.redirect(redirectUrl);
        }

        this.commentReactionServices.create(bindingModel);

        return super.redirect(redirectUrl);
    }

}
