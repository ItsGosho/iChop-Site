package com.ichop.core.areas.report.web.controllers;

import com.ichop.core.areas.report.domain.models.binding.CommentReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.CommentReportServiceModel;
import com.ichop.core.areas.report.domain.models.view.CommentAllReportsViewModel;
import com.ichop.core.areas.report.helpers.CommentAllReportsViewHelper;
import com.ichop.core.areas.report.services.CommentReportServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CommentReportController extends BaseController {

    private final CommentReportServices commentReportServices;
    private final CommentAllReportsViewHelper commentAllReportsViewHelper;

    @Autowired
    public CommentReportController(CommentReportServices commentReportServices, CommentAllReportsViewHelper commentAllReportsViewHelper) {
        this.commentReportServices = commentReportServices;
        this.commentAllReportsViewHelper = commentAllReportsViewHelper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.COMMENT_REPORT_POST)
    public String proceedCommentReport(@PathVariable String commentId, Principal principal, @RequestParam String reason, @Valid @ModelAttribute CommentReportCreateBindingModel bindingModel, BindingResult bindingResult) {

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", bindingModel.getComment().getThread().getId());

        if (bindingResult.hasErrors()) {
            return super.redirect(redirectUrl);
        }

        this.commentReportServices.create(bindingModel);

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.COMMENT_REPORT_DELETE_POST)
    public ModelAndView proceedCommentReportDeletion(@PathVariable String reportId) {

        CommentReportServiceModel commentReport = this.commentReportServices.findById(reportId);
        this.commentReportServices.deleteByModel(commentReport);

        return super.viewWithMessage("notification/info", "Successful!", "You have successful deleted this comment report!");
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.COMMENT_REPORTS_ALL_GET)
    public ModelAndView getAllCommentReportsPage(@PageableDefault(size = 5, sort = "reportDate", direction = Sort.Direction.DESC) Pageable pageable
            , ModelAndView modelAndView) {

        Page<CommentAllReportsViewModel> reports = this.commentAllReportsViewHelper.create(pageable);
        modelAndView.addObject("reports", reports);
        modelAndView.addObject("reportsTable", "report/reports-all-comment");

        return super.page("report/reports-all-base", "Comment Reports", modelAndView);
    }

}
