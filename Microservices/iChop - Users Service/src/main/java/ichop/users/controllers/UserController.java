package ichop.users.controllers;

import ichop.users.domain.models.binding.UserForgottenPasswordBinding;
import ichop.users.constants.URLConstants;
import ichop.users.helpers.ResponseHelpers;
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
