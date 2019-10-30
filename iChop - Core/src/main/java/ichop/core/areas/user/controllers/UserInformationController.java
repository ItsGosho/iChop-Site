package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.requester.UserRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserInformationController {

    private final UserRequester userRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserInformationController(UserRequester userRequester, ResponseHelpers responseHelpers) {
        this.userRequester = userRequester;
        this.responseHelpers = responseHelpers;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.FOLLOW)
    public ResponseEntity follow(@PathVariable(name = "username") String userToFollowUsername, Principal principal) {

        return this.responseHelpers.respondGeneric(null);
    }

}
