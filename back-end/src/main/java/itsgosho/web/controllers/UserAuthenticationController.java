package itsgosho.web.controllers;

import itsgosho.domain.entities.users.User;
import itsgosho.domain.models.binding.UserRegisterBindingModel;
import itsgosho.domain.models.binding.UserResetPasswordBindingModel;
import itsgosho.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserAuthenticationController extends BaseController {

    private final UserServices userServices;

    @Autowired
    public UserAuthenticationController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ModelAndView proceedRegistration(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || !this.userServices.register(userRegisterBindingModel)){
            return this.redirectToRegisterDropdown();
        }

        return this.redirectToLoginDropdown();
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public ModelAndView redirectToLoginDropdown(){
        return super.redirect("/?login=require");
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public ModelAndView redirectToRegisterDropdown(){
        return super.redirect("/?register=require");
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/resetPassword")
    public ModelAndView proceedReset(@Valid UserResetPasswordBindingModel userResetPasswordBindingModel, BindingResult bindingResult){

        if(bindingResult.hasErrors() ||  !this.userServices.sendPasswordResetEmail(userResetPasswordBindingModel)){
            return super.redirect("/ERROR_EMAIL");
        }

        return super.redirect("/SUCCESS_EMAIL");
    }

}
