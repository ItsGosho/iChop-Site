package com.ichop.core.web.controllers.user;

import com.ichop.core.components.jms.JmsServices;
import com.ichop.core.domain.models.binding.user.UserUpdateProfileInformationBindingModel;
import com.ichop.core.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import com.ichop.core.service.user.UserInformationServices;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.service.user.UserServices;
import com.ichop.core.service.user.view.UserViewServices;
import com.ichop.core.web.controllers.BaseController;
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
    private final UserViewServices userViewServices;
    private final JmsServices jmsServices;

    @Autowired
    public UserProfileOptionsController(UserServices userServices, UserInformationServices userInformationServices, UserViewServices userViewServices, JmsServices jmsServices) {
        this.userServices = userServices;
        this.userInformationServices = userInformationServices;
        this.userViewServices = userViewServices;
        this.jmsServices = jmsServices;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET)
    public ModelAndView profileOptionsInformationGet(Principal principal,ModelAndView modelAndView) {
        UserProfileOptionsInformationViewModel userInfo = this.userViewServices.getUserProfileOptionsInformationViewModel(principal.getName());

        modelAndView.addObject("optionsPage","user/options/user-options-profile-information");
        modelAndView.addObject("userInfo",userInfo);

        return super.page("user/options/user-options-base","Options",modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_POST)
    public String profileOptionsInformationUpdate(@Valid UserUpdateProfileInformationBindingModel userUpdateProfileInformationBindingModel, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()){
            return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
        }

        UserServiceModel userServiceModel = this.userServices.findUserByUsername(principal.getName());
        this.userInformationServices.update(userUpdateProfileInformationBindingModel,userServiceModel);
        this.userServices.sendUpdateAvatarRequest(userServiceModel.getUsername(),userUpdateProfileInformationBindingModel.getAvatarBinary());

        return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
    }

}
