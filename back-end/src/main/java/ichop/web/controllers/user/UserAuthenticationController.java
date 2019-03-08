package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.domain.models.binding.user.UserRegisterBindingModel;
import ichop.domain.models.binding.user.UserResetPasswordBindingModel;
import ichop.exceptions.token.TokenNotValidException;
import ichop.exceptions.user.UserPasswordNotValidException;
import ichop.service.token.PasswordResetTokenServices;
import ichop.service.user.UserServices;
import ichop.web.controllers.BaseController;
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

    @PostMapping(URLConstants.USER_REGISTER_POST)
    public String proceedRegistration(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return this.redirectToRegisterDropdown();
        }

        this.userServices.register(userRegisterBindingModel);
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
    public ModelAndView getReset(@RequestParam(required = true) String token){

        if(!this.passwordResetTokenServices.isValid(token)){
            throw new TokenNotValidException();
        }

        return super.page("base-page","auth/reset_password-form","Reset Password");
    }

    @PostMapping(URLConstants.USER_RESET_PASSWORD_POST)
    public ModelAndView proceedPasswordReset(@RequestParam(required = true) String token,@Valid UserResetPasswordBindingModel userResetPasswordBindingModel,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new UserPasswordNotValidException();
        }

        this.userServices.resetPassword(userResetPasswordBindingModel,token);

        return super.viewWithMessage("base-page","notification/info","Your password been reset successfully!");
    }

}
