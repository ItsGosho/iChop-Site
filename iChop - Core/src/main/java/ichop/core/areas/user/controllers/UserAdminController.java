package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.requester.UserRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class UserAdminController {

    private final UserRequester userRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserAdminController(UserRequester userRequester, ResponseHelpers responseHelpers) {
        this.userRequester = userRequester;
        this.responseHelpers = responseHelpers;
    }

    @PostMapping(UserRoutingConstants.ROLE_PROMOTE)
    public ResponseEntity rolePromote(@PathVariable String username) {

        return this.responseHelpers.respondGeneric(null);
    }

    @PostMapping(UserRoutingConstants.ROLE_DEMOTE)
    public ResponseEntity roleDemote(@PathVariable String username) {

        return this.responseHelpers.respondGeneric(null);
    }

}
