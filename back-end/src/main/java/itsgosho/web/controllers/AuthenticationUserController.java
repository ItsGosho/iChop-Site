package itsgosho.web.controllers;

import itsgosho.domain.models.binding.UserRegisterBindingModel;
import itsgosho.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthenticationUserController extends BaseController {

    private final UserServices userServices;

    @Autowired
    public AuthenticationUserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ModelAndView proceedRegistration(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return super.redirect("/");
        }

        if(!this.userServices.register(userRegisterBindingModel)){
            return super.redirect("/");
        }

        return super.page("base-page","shit","iChop");
    }
}
