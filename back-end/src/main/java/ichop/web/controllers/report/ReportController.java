package ichop.web.controllers.report;

import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.comment.CommentServiceModel;
import ichop.domain.models.service.post.PostServiceModel;
import ichop.domain.models.service.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.comment.CommentServices;
import ichop.service.post.PostServices;
import ichop.service.report.CommentReportServices;
import ichop.service.report.PostReportServices;
import ichop.service.report.ThreadReportServices;
import ichop.service.thread.ThreadServices;
import ichop.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ReportController extends BaseController {

    private final CommentServices commentServices;
    private final ThreadServices threadServices;
    private final PostServices postServices;
    private final ThreadReportServices threadReportServices;
    private final CommentReportServices commentReportServices;
    private final PostReportServices postReportServices;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportController(CommentServices commentServices, ThreadServices threadServices, PostServices postServices, ThreadReportServices threadReportServices, CommentReportServices commentReportServices, PostReportServices postReportServices, ModelMapper modelMapper) {
        this.commentServices = commentServices;
        this.threadServices = threadServices;
        this.postServices = postServices;
        this.threadReportServices = threadReportServices;
        this.commentReportServices = commentReportServices;
        this.postReportServices = postReportServices;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REPORT_POST)
    public String reportComment(@PathVariable String commentId, Principal principal, @RequestParam String reason) {
        CommentServiceModel commentServiceModel = this.commentServices.findCommentById(commentId);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.commentReportServices.createReport(commentServiceModel, userServiceModel, reason);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}",commentServiceModel.getThread().getId());

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REPORT_POST)
    public String reportThread(@PathVariable String threadId, Principal principal, @RequestParam String reason) {
        ThreadServiceModel threadServiceModel = this.threadServices.findThreadById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.threadReportServices.createReport(threadServiceModel, userServiceModel, reason);

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}",threadServiceModel.getId());

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.POST_REPORT_POST)
    public String reportPost(@PathVariable String postId, Principal principal, @RequestParam String reason) {

        PostServiceModel postServiceModel = this.postServices.findPostById(postId);
        UserServiceModel userServiceModel = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.postReportServices.createReport(postServiceModel, userServiceModel, reason);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",postServiceModel.getCreator().getUsername());

        return super.redirect(redirectUrl);
    }
}
