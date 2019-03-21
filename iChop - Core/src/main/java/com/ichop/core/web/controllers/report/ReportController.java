package com.ichop.core.web.controllers.report;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.service.comment.CommentServiceModel;
import com.ichop.core.domain.models.service.post.PostServiceModel;
import com.ichop.core.domain.models.service.thread.ThreadServiceModel;
import com.ichop.core.web.controllers.BaseController;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.service.comment.CommentServices;
import com.ichop.core.service.post.PostServices;
import com.ichop.core.service.report.CommentReportServices;
import com.ichop.core.service.report.PostReportServices;
import com.ichop.core.service.report.ThreadReportServices;
import com.ichop.core.service.thread.ThreadServices;
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
