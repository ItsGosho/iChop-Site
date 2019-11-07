package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.requesters.UserRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(UserRoutingConstants.FIND_BY)
    public ResponseEntity findByUsername(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        JmsReplyModel reply = null;

        if (username != null) {
            reply = this.userRequester.findByUsername(username);
        } else if (email != null) {
            reply = this.userRequester.findByEmail(email);
        }

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(UserRoutingConstants.ROLE_PROMOTE)
    public ResponseEntity rolePromote(@PathVariable String username) {
        JmsReplyModel reply = this.userRequester.promote(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(UserRoutingConstants.ROLE_DEMOTE)
    public ResponseEntity roleDemote(@PathVariable String username) {
        JmsReplyModel reply = this.userRequester.demote(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.ROLE_HAS_NEXT)
    public ResponseEntity roleHasNext(@PathVariable String username) {
        JmsReplyModel reply = this.userRequester.hasNextRole(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.ROLE_HAS_PREVIOUS)
    public ResponseEntity roleHasPrevious(@PathVariable String username) {
        JmsReplyModel reply = this.userRequester.hasPreviousRole(username);

        return this.responseHelpers.respondGeneric(reply);
    }

}
