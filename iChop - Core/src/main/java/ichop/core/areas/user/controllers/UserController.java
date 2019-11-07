package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.core.areas.user.models.jms.password.change.UserChangePasswordRequest;
import ichop.core.areas.user.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.core.areas.user.requesters.UserRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity changePassword(@RequestBody UserChangePasswordRequest changePasswordRequest, Principal principal) {
        changePasswordRequest.setUsername(principal.getName());

        JmsReplyModel reply = this.userRequester.changePassword(changePasswordRequest);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.FORGOTTEN_PASSWORD)
    public ResponseEntity forgottenPassword(@RequestBody UserForgottenPasswordRequest forgottenPasswordRequest) {
        JmsReplyModel reply = this.userRequester.forgottenPassword(forgottenPasswordRequest);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD_BY_TOKEN)
    public ResponseEntity changePasswordByToken(@RequestBody UserChangePasswordByTokenRequest changePasswordByTokenRequest) {
        JmsReplyModel reply = this.userRequester.changePasswordByToken(changePasswordByTokenRequest);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(UserRoutingConstants.FIND_BY)
    public ResponseEntity findBySensitive(@RequestParam String username) {


        return this.responseHelpers.respondGeneric(null);
    }

    /*@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(UserRoutingConstants.ALL)
    public ResponseEntity all(Pageable pageable) {

        //UsersAllPageableReply reply = this.userRequester.findAllPageable(pageable);

        return this.responseHelpers.respondGeneric(null);
    }
*/
}
