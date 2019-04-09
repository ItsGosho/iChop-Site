package com.ichop.core.areas.report.web.controllers;

import com.ichop.core.areas.comment.domain.models.service.CommentServiceModel;
import com.ichop.core.areas.comment.services.CommentServices;
import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.report.domain.models.view.CommentAllReportsViewModel;
import com.ichop.core.areas.report.helpers.CommentAllReportsViewHelper;
import com.ichop.core.areas.report.services.CommentReportServices;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class CommentReportController extends BaseController {

    private final CommentServices commentServices;
    private final CommentReportServices commentReportServices;
    private final CommentAllReportsViewHelper commentAllReportsViewHelper;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentReportController(CommentServices commentServices, CommentReportServices commentReportServices, CommentAllReportsViewHelper commentAllReportsViewHelper, ModelMapper modelMapper) {
        this.commentServices = commentServices;
        this.commentReportServices = commentReportServices;
        this.commentAllReportsViewHelper = commentAllReportsViewHelper;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REPORT_POST)
    public String proceedCommentReport(@PathVariable String commentId, Principal principal, @RequestParam String reason) {

        CommentServiceModel commentServiceModel = this.commentServices.findById(commentId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);
        CommentReportCreateBindingModel bindingModel = new CommentReportCreateBindingModel();
        bindingModel.setReason(reason);
        bindingModel.setUser(userServiceModel);
        bindingModel.setComment(commentServiceModel);

        this.commentReportServices.create(bindingModel);
        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}",commentServiceModel.getThread().getId());

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.COMMENT_REPORT_DELETE_POST)
    public ModelAndView proceedCommentReportDeletion(@PathVariable String reportId) {

        CommentReportServiceModel commentReport = this.commentReportServices.findById(reportId);
        this.commentReportServices.deleteByModel(commentReport);

        return super.viewWithMessage("notification/info","Successful!","You have successful deleted this comment report!");
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.COMMENT_REPORTS_ALL_GET)
    public ModelAndView getAllCommentReportsPage(@PageableDefault(size = 5, sort = "reportDate", direction = Sort.Direction.DESC) Pageable pageable
            , ModelAndView modelAndView) {

        Page<CommentAllReportsViewModel> reports = this.commentAllReportsViewHelper.create(pageable);
        modelAndView.addObject("reports",reports);
        modelAndView.addObject("reportsTable","report/reports-all-comment");

        return super.page("report/reports-all-base","Comment Reports",modelAndView);
    }

}
