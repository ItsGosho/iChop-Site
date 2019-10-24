package ichop.core.areas.report.web.fillers;

import ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import ichop.core.areas.comment.services.CommentServices;
import ichop.core.areas.post.domain.models.service.PostServiceModel;
import ichop.core.areas.post.services.PostServices;
import ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import ichop.core.areas.report.web.controllers.CommentReportController;
import ichop.core.areas.report.web.controllers.PostReportController;
import ichop.core.areas.report.web.controllers.ThreadReportController;
import ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import ichop.core.areas.thread.services.ThreadServices;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.validators.aspects.SkipOnNull;
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
