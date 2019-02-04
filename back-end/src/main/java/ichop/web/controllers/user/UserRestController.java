package ichop.web.controllers.user;

import com.google.gson.Gson;
import ichop.constants.URLConstants;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserRestController {

    private final UserServices userServices;

    @Autowired
    public UserRestController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = URLConstants.USER_SEND_EMAIL_RESET_PASSWORD_POST,produces = "application/json")
    @ResponseBody
    public String sendEmailForReset(@Valid UserForgottenPasswordBindingModel userForgottenPasswordBindingModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Gson().toJson(false);
        }

        this.userServices.sendPasswordResetEmail(userForgottenPasswordBindingModel);

        return new Gson().toJson(true);
    }

}
