package com.ichop.core.areas.report.web.controllers;

import com.ichop.core.areas.report.domain.models.binding.PostReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.PostReportServiceModel;
import com.ichop.core.areas.report.domain.models.view.PostAllReportsViewModel;
import com.ichop.core.areas.report.helpers.PostAllReportsViewHelper;
import com.ichop.core.areas.report.services.PostReportServices;
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
public class PostReportController extends BaseController {

    private final PostReportServices postReportServices;
    private final PostAllReportsViewHelper postAllReportsViewHelper;

    @Autowired
    public PostReportController(PostReportServices postReportServices, PostAllReportsViewHelper postAllReportsViewHelper) {
        this.postReportServices = postReportServices;
        this.postAllReportsViewHelper = postAllReportsViewHelper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.POST_REPORT_POST)
    public String proceedPostReport(@PathVariable String postId, Principal principal, @RequestParam String reason, @Valid @ModelAttribute PostReportCreateBindingModel bindingModel, BindingResult bindingResult) {

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}", bindingModel.getUser().getUsername());

        if (bindingResult.hasErrors()) {
            return super.redirect(redirectUrl);
        }

        this.postReportServices.create(bindingModel);

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.POST_REPORT_DELETE_POST)
    public ModelAndView proceedPostReportDeletion(@PathVariable String reportId) {

        PostReportServiceModel postReport = this.postReportServices.findById(reportId);
        this.postReportServices.deleteByModel(postReport);

        return super.viewWithMessage("notification/info", "Successful!", "You have successful deleted this post report!");
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.POST_REPORTS_ALL_GET)
    public ModelAndView getAllThreadReportsPage(@PageableDefault(size = 5, sort = "reportDate", direction = Sort.Direction.DESC) Pageable pageable
            , ModelAndView modelAndView) {

        Page<PostAllReportsViewModel> reports = this.postAllReportsViewHelper.create(pageable);
        modelAndView.addObject("reports", reports);
        modelAndView.addObject("reportsTable", "report/reports-all-posts");

        return super.page("report/reports-all-base", "Post Reports", modelAndView);
    }


}
