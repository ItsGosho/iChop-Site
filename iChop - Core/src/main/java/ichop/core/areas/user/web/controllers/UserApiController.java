package ichop.core.areas.user.web.controllers;

import com.google.gson.Gson;
import ichop.core.areas.user.domain.models.binding.UserForgottenPasswordBindingModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserApiController {

    private final UserServices userServices;

    @Autowired
    public UserApiController(UserServices userServices) {
        this.userServices = userServices;
    }


    @GetMapping(value = URLConstants.USER_EXISTS_GET, produces = "application/json")
    @ResponseBody
    public String isUserExists(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {

        if (username != null) {
            return new Gson().toJson(this.userServices.isUserExistsByUsername(username));
        } else if (email != null) {
            return new Gson().toJson(this.userServices.isUserExistsByEmail(email));
        }

        throw new IllegalArgumentException();
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = URLConstants.USER_SEND_EMAIL_RESET_PASSWORD_POST, produces = "application/json")
    @ResponseBody
    public String sendEmailForReset(@Valid UserForgottenPasswordBindingModel userForgottenPasswordBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new Gson().toJson(false);
        }

        this.userServices.sendPasswordResetEmail(userForgottenPasswordBindingModel);

        return new Gson().toJson(true);
    }

}
