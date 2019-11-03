package ichop.core.areas.security.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.requester.UserRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController {

    private final UserRequester userRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserAuthenticationController(UserRequester userRequester, ResponseHelpers responseHelpers) {
        this.userRequester = userRequester;
        this.responseHelpers = responseHelpers;
    }


    @PostMapping(UserRoutingConstants.REGISTER)
    public ResponseEntity proceedRegistration(UserRegisterRequest request) {
        JmsReplyModel reply = this.userRequester.register(request);

        return this.responseHelpers.respondGeneric(reply);
    }

}
