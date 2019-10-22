package com.ichop.core.areas.report.web.controllers;

import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import com.ichop.core.areas.report.domain.models.view.ThreadAllReportsViewModel;
import com.ichop.core.areas.report.helpers.ThreadAllReportsViewHelper;
import com.ichop.core.areas.report.services.ThreadReportServices;
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
public class ThreadReportController extends BaseController {

    private final ThreadReportServices threadReportServices;
    private final ThreadAllReportsViewHelper threadAllReportsViewHelper;

    @Autowired
    public ThreadReportController(ThreadReportServices threadReportServices, ThreadAllReportsViewHelper threadAllReportsViewHelper) {
        this.threadReportServices = threadReportServices;
        this.threadAllReportsViewHelper = threadAllReportsViewHelper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REPORT_POST)
    public String proceedThreadReport(@PathVariable String threadId, Principal principal, @RequestParam String reason, @Valid @ModelAttribute ThreadReportCreateBindingModel bindingModel, BindingResult bindingResult) {

        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", bindingModel.getThread().getId());

        if (bindingResult.hasErrors()) {
            return super.redirect(redirectUrl);
        }

        this.threadReportServices.create(bindingModel);

        return super.redirect(redirectUrl);
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(URLConstants.THREAD_REPORT_DELETE_POST)
    public ModelAndView proceedThreadReportDeletion(@PathVariable String reportId) {

        ThreadReportServiceModel threadReport = this.threadReportServices.findById(reportId);
        this.threadReportServices.deleteByModel(threadReport);

        return super.viewWithMessage("notification/info", "Successful!", "You have successful deleted this thread report!");
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @GetMapping(URLConstants.THREAD_REPORTS_ALL_GET)
    public ModelAndView getAllThreadReportsPage(@PageableDefault(size = 5, sort = "reportDate", direction = Sort.Direction.DESC) Pageable pageable
            , ModelAndView modelAndView) {

        Page<ThreadAllReportsViewModel> reports = this.threadAllReportsViewHelper.create(pageable);
        modelAndView.addObject("reportsTable", "report/reports-all-thread");
        modelAndView.addObject("reports", reports);

        return super.page("report/reports-all-base", "Thread Reports", modelAndView);
    }

}
