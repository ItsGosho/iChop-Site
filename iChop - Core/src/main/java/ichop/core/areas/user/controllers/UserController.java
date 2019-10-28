package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordReply;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordReply;
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
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserController(UserRequester userRequester, ResponseHelpers responseHelpers) {
        this.userRequester = userRequester;
        this.responseHelpers = responseHelpers;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD)
    public ResponseEntity changePassword(UserChangePasswordRequest changePasswordRequest, Principal principal) {
        changePasswordRequest.setEmail(principal.getName());

        UserChangePasswordReply reply = this.userRequester.changePassword(changePasswordRequest);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.FORGOTTEN_PASSWORD)
    public ResponseEntity forgottenPassword(UserForgottenPasswordRequest forgottenPasswordRequest) {
        UserForgottenPasswordReply reply = this.userRequester.forgottenPassword(forgottenPasswordRequest);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD_BY_TOKEN)
    public ResponseEntity changePasswordByToken(UserChangePasswordByTokenRequest changePasswordByTokenRequest) {
        UserChangePasswordByTokenReply reply = this.userRequester.changePasswordByToken(changePasswordByTokenRequest);

        return this.responseHelpers.respondGeneric(reply);
    }
}
