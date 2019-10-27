package ichop.core.areas.user.controllers;

import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.core.areas.user.requester.UserRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private final UserRequester userRequester;

    @Autowired
    public UserController(UserRequester userRequester) {
        this.userRequester = userRequester;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD)
    private ResponseEntity changePassword(UserChangePasswordRequest changePasswordRequest, Principal principal) {
        changePasswordRequest.setEmail(principal.getName());

        return null;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.FORGOTTEN_PASSWORD)
    private ResponseEntity forgottenPassword(UserForgottenPasswordRequest forgottenPasswordRequest) {

        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD_BY_TOKEN)
    private ResponseEntity changePasswordByToken(UserChangePasswordByTokenRequest changePasswordByTokenRequest) {

        return null;
    }
}
