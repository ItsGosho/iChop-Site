package com.ichop.core.areas.report.web.controllers;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.post.domain.models.service.PostServiceModel;
import com.ichop.core.areas.post.services.PostServices;
import com.ichop.core.areas.report.services.CommentReportServices;
import com.ichop.core.areas.report.services.PostReportServices;
import com.ichop.core.areas.report.services.ThreadReportServices;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.services.ThreadServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
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
    public String proceedCommentReport(@PathVariable String commentId, Principal principal, @RequestParam String reason) {

        CommentServiceModel commentServiceModel = this.commentServices.findById(commentId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.commentReportServices.create(commentServiceModel, userServiceModel, reason);
        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}",commentServiceModel.getThread().getId());

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REPORT_POST)
    public String proceedThreadReport(@PathVariable String threadId, Principal principal, @RequestParam String reason) {

        ThreadServiceModel threadServiceModel = this.threadServices.findById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.threadReportServices.create(threadServiceModel, userServiceModel, reason);
        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}",threadServiceModel.getId());

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.POST_REPORT_POST)
    public String proceedPostReport(@PathVariable String postId, Principal principal, @RequestParam String reason) {

        PostServiceModel postServiceModel = this.postServices.findById(postId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);

        this.postReportServices.create(postServiceModel, userServiceModel, reason);
        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",postServiceModel.getCreator().getUsername());

        return super.redirect(redirectUrl);
    }
}
