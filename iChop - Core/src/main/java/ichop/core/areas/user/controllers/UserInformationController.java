package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.requester.UserInformationRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserInformationController {

    private final UserInformationRequester userInformationRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserInformationController(UserInformationRequester userInformationRequester, ResponseHelpers responseHelpers) {
        this.userInformationRequester = userInformationRequester;
        this.responseHelpers = responseHelpers;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.UPDATE_INFORMATION)
    public ResponseEntity update(@PathVariable String username) {

        return this.responseHelpers.respondGeneric(null);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.RETRIEVE_INFORMATION)
    public ResponseEntity retrieve(@PathVariable String username) {

        return this.responseHelpers.respondGeneric(null);
    }

}
