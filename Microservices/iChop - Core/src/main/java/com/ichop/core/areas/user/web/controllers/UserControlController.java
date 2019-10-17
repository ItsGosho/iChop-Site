package com.ichop.core.areas.user.web.controllers;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_control.UserControlHomeViewModel;
import com.ichop.core.areas.user.domain.models.view.user_control.UserRoleManagementUserControlViewModel;
import com.ichop.core.areas.user.helpers.view.user_control.UserControlHomeViewHelper;
import com.ichop.core.areas.user.helpers.view.user_control.UserRoleManagementUserControlViewHelper;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserControlController extends BaseController {

    private final UserServices userServices;
    private final UserControlHomeViewHelper userControlHomeViewHelper;
    private final UserRoleManagementUserControlViewHelper userRoleManagementUserControlViewHelper;

    @Autowired
    public UserControlController(UserServices userServices, UserControlHomeViewHelper userControlHomeViewHelper, UserRoleManagementUserControlViewHelper userRoleManagementUserControlViewHelper) {
        this.userServices = userServices;
        this.userControlHomeViewHelper = userControlHomeViewHelper;
        this.userRoleManagementUserControlViewHelper = userRoleManagementUserControlViewHelper;
    }


    @GetMapping(URLConstants.USER_CONTROL_BASE_GET)
    public ModelAndView getUserControl(ModelAndView modelAndView, @PathVariable(value = "username") String username) {

        UserControlHomeViewModel user = this.userControlHomeViewHelper.create(username);

        modelAndView.addObject("controlPage","user/control/user-control-core_information");
        modelAndView.addObject("user",user);

        return super.page("user/control/user-control-base","Control",modelAndView);
    }

    @GetMapping(URLConstants.USER_CONTROL_ROLE_MANAGEMENT_GET)
    public ModelAndView getUserRoleManagement(ModelAndView modelAndView,@PathVariable(value = "username") String username) {

        UserRoleManagementUserControlViewModel user = this.userRoleManagementUserControlViewHelper.create(username);

        modelAndView.addObject("controlPage","user/control/user-control-role_management");
        modelAndView.addObject("user",user);

        return super.page("user/control/user-control-base","Control - Role Management",modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_CONTROL_ROLE_MANAGEMENT_PROMOTE_USER_POST)
    public String userProceedRolePromote(@PathVariable(value = "username") String username) {

        UserServiceModel user = this.userServices.findUserByUsername(username);
        this.userServices.promote(user);

        String redirectUrl = URLConstants.USER_CONTROL_ROLE_MANAGEMENT_GET.replace("{username}",user.getUsername());
        return super.redirect(redirectUrl);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_CONTROL_ROLE_MANAGEMENT_DEMOTE_USER_POST)
    public String userProceedRoleDemote(ModelAndView modelAndView,@PathVariable(value = "username") String username) {

        UserServiceModel user = this.userServices.findUserByUsername(username);
        this.userServices.demote(user);

        String redirectUrl = URLConstants.USER_CONTROL_ROLE_MANAGEMENT_GET.replace("{username}",user.getUsername());
        return super.redirect(redirectUrl);
    }

}
