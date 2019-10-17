package com.ichop.core.areas.report.web.fillers;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.services.PostServices;
import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.web.controllers.CommentReportController;
import com.ichop.core.areas.report.web.controllers.PostReportController;
import com.ichop.core.areas.report.web.controllers.ThreadReportController;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.validators.aspects.SkipOnNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@ControllerAdvice(assignableTypes = {CommentReportController.class, PostReportController.class, ThreadReportController.class})
public class ReportBindingModelsFillers {

    private final UserServices userServices;
    private final CommentServices commentServices;
    private final ThreadServices threadServices;
    private final PostServices postServices;

    @Autowired
    public ReportBindingModelsFillers(UserServices userServices, CommentServices commentServices, ThreadServices threadServices, PostServices postServices) {
        this.userServices = userServices;
        this.commentServices = commentServices;
        this.threadServices = threadServices;
        this.postServices = postServices;
    }

    @SkipOnNull
    @ModelAttribute
    public ThreadReportCreateBindingModel fill(ThreadReportCreateBindingModel bindingModel,
                                               @PathVariable(required = false) String threadId,
                                               Principal principal) {
        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());
        ThreadServiceModel thread = this.threadServices.findById(threadId);

        bindingModel.setUser(user);
        bindingModel.setThread(thread);

        return bindingModel;
    }

    @SkipOnNull
    @ModelAttribute
    public CommentReportCreateBindingModel fill(CommentReportCreateBindingModel bindingModel,
                                                @PathVariable(required = false) String commentId,
                                                Principal principal) {
        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());
        CommentServiceModel comment = this.commentServices.findById(commentId);

        bindingModel.setUser(user);
        bindingModel.setComment(comment);

        return bindingModel;
    }

    @SkipOnNull
    @ModelAttribute
    public PostReportCreateBindingModel fill(PostReportCreateBindingModel bindingModel,
                                             @PathVariable(required = false) String postId,
                                             Principal principal) {
        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());
        PostServiceModel post = this.postServices.findById(postId);

        bindingModel.setUser(user);
        bindingModel.setPost(post);

        return bindingModel;
    }

}
