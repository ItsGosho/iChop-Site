package com.ichop.core.areas.user.web.controllers;

import com.ichop.core.areas.user.services.UserInformationServices;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserUpdateProfileInformationBindingModel;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import com.ichop.core.areas.user.helpers.view.user_options.UserProfileOptionsInformationViewHelper;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserProfileOptionsController extends BaseController {

    private final UserServices userServices;
    private final UserInformationServices userInformationServices;
    private final UserProfileOptionsInformationViewHelper userProfileOptionsInformationViewHelper;

    @Autowired
    public UserProfileOptionsController(UserServices userServices, UserInformationServices userInformationServices, UserProfileOptionsInformationViewHelper userProfileOptionsInformationViewHelper) {
        this.userServices = userServices;
        this.userInformationServices = userInformationServices;
        this.userProfileOptionsInformationViewHelper = userProfileOptionsInformationViewHelper;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET)
    public ModelAndView getProfileInformationOptions(Principal principal,ModelAndView modelAndView) {
        this.userInformationServices.createFirstTime(this.userServices.findUserByUsername(principal.getName()));
        UserProfileOptionsInformationViewModel userInfo = this.userProfileOptionsInformationViewHelper.create(principal.getName());

        modelAndView.addObject("optionsPage","user/options/user-options-profile-information");
        modelAndView.addObject("userInfo",userInfo);

        return super.page("user/options/user-options-base","Options",modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_POST)
    public String proceedProfileInformationUpdate(@Valid UserUpdateProfileInformationBindingModel bindingModel, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()){
            return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
        }

        UserServiceModel userServiceModel = this.userServices.findUserByUsername(principal.getName());
        bindingModel.setUser(userServiceModel);
        this.userInformationServices.update(bindingModel);
        this.userServices.sendUpdateAvatarRequest(userServiceModel.getUsername(),bindingModel.getAvatarBinary());

        return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_CHANGE_PASSWORD_GET)
    public ModelAndView getChangePasswordPage(ModelAndView modelAndView) {

        modelAndView.addObject("optionsPage","user/options/user-options-change_password");

        return super.page("user/options/user-options-base","Change Password",modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_PROFILE_OPTIONS_CHANGE_PASSWORD_POST)
    public ModelAndView proceedChangePassword(Principal principal, UserResetPasswordBindingModel userResetPasswordBindingModel) {

        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());
        this.userServices.resetPassword(userResetPasswordBindingModel,user);

        return super.viewWithMessage("notification/info","Successful Change","You have successful changed your password!");
    }

}
