package com.ichop.core.areas.user.web.controllers;

import com.ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import com.ichop.core.areas.user.domain.models.binding.UserResetPasswordBindingModel;
import com.ichop.core.areas.user.services.UserWebStorageJmsServices;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.areas.token.exceptions.TokenNotValidException;
import com.ichop.core.areas.user.exceptions.UserPasswordNotValidException;
import com.ichop.core.areas.token.services.PasswordResetTokenServices;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController extends BaseController {

    private final UserServices userServices;
    private final PasswordResetTokenServices passwordResetTokenServices;
    private final UserWebStorageJmsServices userWebStorageJmsServices;

    @Autowired
    public UserAuthenticationController(UserServices userServices, PasswordResetTokenServices passwordResetTokenServices, UserWebStorageJmsServices userWebStorageJmsServices) {
        this.userServices = userServices;
        this.passwordResetTokenServices = passwordResetTokenServices;
        this.userWebStorageJmsServices = userWebStorageJmsServices;
    }

    @PostMapping(URLConstants.USER_REGISTER_POST)
    public String proceedRegistration(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return this.redirectToRegisterDropdown();
        }

        this.userServices.register(userRegisterBindingModel);
        this.userWebStorageJmsServices.sendUpdateAvatarRequestWithInitialImage(userRegisterBindingModel.getUsername());
        return this.redirectToLoginDropdown();
    }

    @GetMapping(URLConstants.USER_LOGIN_GET)
    public String redirectToLoginDropdown(){
        return super.redirect("/?login=require");
    }

    @GetMapping(URLConstants.USER_REGISTER_GET)
    public String redirectToRegisterDropdown(){
        return super.redirect("/?register=require");
    }

    @GetMapping(URLConstants.USER_RESET_PASSWORD_GET)
    public ModelAndView getPasswordReset(@RequestParam(required = true) String token){

        if(!this.passwordResetTokenServices.isValid(token)){
            throw new TokenNotValidException();
        }

        return super.page("auth/reset_password-form","Reset Password");
    }

    @PostMapping(URLConstants.USER_RESET_PASSWORD_POST)
    public ModelAndView proceedPasswordReset(@RequestParam(required = true) String token, @Valid UserResetPasswordBindingModel userResetPasswordBindingModel, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new UserPasswordNotValidException();
        }

        this.userServices.resetPassword(userResetPasswordBindingModel,token);

        return super.viewWithMessage("notification/info","Successful resetted password!","Your password been reset successfully!");
    }

}
