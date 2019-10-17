package com.ichop.core.web.controllers;

import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.areas.thread.domain.models.view.thread_homepage.ThreadHomepageViewModel;
import com.ichop.core.areas.thread.helpers.view.thread_homepage.ThreadHomepageViewHelper;
import com.ichop.core.areas.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController extends BaseController {


    private final ThreadHomepageViewHelper threadHomepageViewHelper;

    @Autowired
    public HomeController(ThreadHomepageViewHelper threadHomepageViewHelper, UserServices userServices) {
        this.threadHomepageViewHelper = threadHomepageViewHelper;
    }


    @GetMapping(value = URLConstants.HOME_GET)
    public ModelAndView home(ModelAndView modelAndView,
                             @PageableDefault(size = 3, sort = "createdOn", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ThreadHomepageViewModel> pages = this.threadHomepageViewHelper.create(pageable);

        modelAndView.addObject("threads", pages);
        modelAndView.addObject("totalPages", pages.getTotalPages());

        return super.page("home-page", "Home", modelAndView);
    }


    // @PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("@userServicesImp.findUserByUsername(#user1Username) != null")
    @GetMapping(value = URLConstants.TEST)
    @ResponseBody
    public String testHome(ModelAndView modelAndView, HttpServletRequest httpServletRequest, @RequestParam(required = true) String user1Username) throws IOException {


        return "<h1>"+ "brrr" + "</h1>";
    }
}
