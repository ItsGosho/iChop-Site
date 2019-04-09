package com.ichop.core.areas.report.web.controllers;

import com.ichop.core.areas.report.domain.models.binding.ThreadReportCreateBindingModel;
import com.ichop.core.areas.report.domain.models.service.ThreadReportServiceModel;
import com.ichop.core.areas.report.domain.models.view.ThreadAllReportsViewModel;
import com.ichop.core.areas.report.helpers.ThreadAllReportsViewHelper;
import com.ichop.core.areas.report.services.ThreadReportServices;
import com.ichop.core.areas.thread.domain.models.service.ThreadServiceModel;
import com.ichop.core.areas.thread.services.ThreadServices;
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
public class ThreadReportController extends BaseController {

    private final ThreadServices threadServices;
    private final ThreadReportServices threadReportServices;
    private final ThreadAllReportsViewHelper threadAllReportsViewHelper;
    private final ModelMapper modelMapper;

    @Autowired
    public ThreadReportController(ThreadServices threadServices, ThreadReportServices threadReportServices, ThreadAllReportsViewHelper threadAllReportsViewHelper, ModelMapper modelMapper) {
        this.threadServices = threadServices;
        this.threadReportServices = threadReportServices;
        this.threadAllReportsViewHelper = threadAllReportsViewHelper;
        this.modelMapper = modelMapper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.THREAD_REPORT_POST)
    public String proceedThreadReport(@PathVariable String threadId, Principal principal, @RequestParam String reason) {

        ThreadServiceModel threadServiceModel = this.threadServices.findById(threadId);
        UserServiceModel userServiceModel = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);
        ThreadReportCreateBindingModel bindingModel = new ThreadReportCreateBindingModel();
        bindingModel.setReason(reason);
        bindingModel.setUser(userServiceModel);
        bindingModel.setThread(threadServiceModel);

        this.threadReportServices.create(bindingModel);
        String redirectUrl = URLConstants.THREAD_READ_GET.replace("{threadId}", threadServiceModel.getId());

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
        modelAndView.addObject("reportsTable","report/reports-all-thread");
        modelAndView.addObject("reports",reports);

        return super.page("report/reports-all-base","Thread Reports",modelAndView);
    }

}
