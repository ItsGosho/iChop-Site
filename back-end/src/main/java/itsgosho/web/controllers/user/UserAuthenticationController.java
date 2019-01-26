package itsgosho.web.controllers.user;

import itsgosho.domain.models.binding.user.UserRegisterBindingModel;
import itsgosho.domain.models.binding.user.UserResetPasswordBindingModel;
import itsgosho.service.token.PasswordResetTokenServices;
import itsgosho.service.user.UserServices;
import itsgosho.web.controllers.BaseController;
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

    @Autowired
    public UserAuthenticationController(UserServices userServices, PasswordResetTokenServices passwordResetTokenServices) {
        this.userServices = userServices;
        this.passwordResetTokenServices = passwordResetTokenServices;
    }

    @PostMapping("/register")
    public ModelAndView proceedRegistration(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return this.redirectToRegisterDropdown();
        }
        this.userServices.register(userRegisterBindingModel);
        return this.redirectToLoginDropdown();
    }

    @GetMapping("/login")
    public ModelAndView redirectToLoginDropdown(){
        return super.redirect("/?login=require");
    }

    @GetMapping("/register")
    public ModelAndView redirectToRegisterDropdown(){
        return super.redirect("/?register=require");
    }

    @GetMapping("/reset/password")
    public ModelAndView getReset(@RequestParam(required = true) String token){

        if(!this.passwordResetTokenServices.isValid(token)){
            return super.viewWithMessage("base-page","notification/error","The provided token is invalid!");
        }

        return super.page("base-page","auth/reset_password-form","Reset Password");
    }

    @PostMapping("/reset/password")
    public ModelAndView proceedPasswordReset(@RequestParam(required = true) String token,@Valid UserResetPasswordBindingModel userResetPasswordBindingModel,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return super.viewWithMessage("base-page","notification/error","Something with your input data went wrong :/");
        }

        this.userServices.resetPassword(userResetPasswordBindingModel,token);

        return super.viewWithMessage("base-page","notification/info","Your password been reset successfully!");
    }

}
