package com.ichop.core.web.controllers.user;

import com.ichop.core.domain.models.view.user_control.UserControlHomeViewModel;
import com.ichop.core.domain.models.view.user_control.UserControlRoleManagementViewModel;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.service.user.UserServices;
import com.ichop.core.service.user.view.UserViewServices;
import com.ichop.core.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserControlController extends BaseController {

    private final UserServices userServices;
    private final UserViewServices userViewServices;

    @Autowired
    public UserControlController(UserServices userServices, UserViewServices userViewServices) {
        this.userServices = userServices;
        this.userViewServices = userViewServices;
    }

    @GetMapping(URLConstants.USER_CONTROL_BASE_GET)
    public ModelAndView userControlBase(ModelAndView modelAndView, @PathVariable(value = "username") String username) {

        UserControlHomeViewModel user = this.userViewServices.getUserControlHomeViewModel(username);

        modelAndView.addObject("controlPage","user/control/user-control-core_information");
        modelAndView.addObject("user",user);

        return super.page("user/control/user-control-base","Control",modelAndView);
    }

    @GetMapping(URLConstants.USER_CONTROL_ROLE_MANAGEMENT_GET)
    public ModelAndView userControlHome(ModelAndView modelAndView,@PathVariable(value = "username") String username) {

        UserControlRoleManagementViewModel user = this.userViewServices.getUserControlRoleManagementViewModel(username);

        modelAndView.addObject("controlPage","user/control/user-control-role_management");
        modelAndView.addObject("user",user);

        return super.page("user/control/user-control-base","Control - Role Management",modelAndView);
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_CONTROL_ROLE_MANAGEMENT_PROMOTE_USER_POST)
    public String userRolePromote(ModelAndView modelAndView, @PathVariable(value = "username") String username, Principal principal) {

        UserServiceModel user = this.userServices.findUserByUsername(username);

        UserServiceModel promotedUser = this.userServices.promote(user);

        String redirectUrl = URLConstants.USER_CONTROL_ROLE_MANAGEMENT_GET.replace("{username}",user.getUsername());
        return super.redirect(redirectUrl);
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_CONTROL_ROLE_MANAGEMENT_DEMOTE_USER_POST)
    public String userRoleDemote(ModelAndView modelAndView,@PathVariable(value = "username") String username) {

        UserServiceModel user = this.userServices.findUserByUsername(username);

        this.userServices.demote(user);

        String redirectUrl = URLConstants.USER_CONTROL_ROLE_MANAGEMENT_GET.replace("{username}",user.getUsername());
        return super.redirect(redirectUrl);
    }

}
