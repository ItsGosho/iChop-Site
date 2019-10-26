package ichop.core.areas.user.controllers;

import ichop.core.areas.user.domain.models.binding.UserForgottenPasswordBinding;
import ichop.core.constants.URLConstants;
import ichop.core.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserController(ResponseHelpers responseHelpers) {
        this.responseHelpers = responseHelpers;
    }

    @GetMapping(value = URLConstants.USER_SEND_EMAIL_RESET_PASSWORD)
    public void sendPasswordResetEmail(Principal principal, UserForgottenPasswordBinding bindingModel) {
        System.out.println();
    }

}
