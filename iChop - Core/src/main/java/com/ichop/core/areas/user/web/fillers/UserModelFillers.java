package com.ichop.core.areas.user.web.fillers;

import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModelByToken;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModelByUser;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.areas.user.web.controllers.UserAuthenticationController;
import com.ichop.core.areas.user.web.controllers.UserProfileOptionsController;
import com.ichop.core.validators.method.SkipOnNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@ControllerAdvice(assignableTypes = {UserAuthenticationController.class, UserProfileOptionsController.class})
public class UserModelFillers {

    private final UserServices userServices;


    @Autowired
    public UserModelFillers(UserServices userServices) {
        this.userServices = userServices;
    }

    @SkipOnNull
    @ModelAttribute
    public UserResetPasswordBindingModelByToken fill(UserResetPasswordBindingModelByToken bindingModel, @RequestParam(required = false) String token, Principal principal) {
        bindingModel.setToken(token);

        return bindingModel;
    }

    @SkipOnNull
    @ModelAttribute
    public UserResetPasswordBindingModelByUser fill(UserResetPasswordBindingModelByUser bindingModel, Principal principal) {
        UserServiceModel user = this.userServices.findUserByUsername(principal.getName());

        bindingModel.setUser(user);

        return bindingModel;
    }

}
