package ichop.users.controllers;

import ichop.users.services.UserServices;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class UserAdminController {

    private final UserServices userServices;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserAdminController(UserServices userServices, ResponseHelpers responseHelpers) {
        this.userServices = userServices;
        this.responseHelpers = responseHelpers;
    }

   /* @GetMapping(UserRoutingConstants.ADMIN_FIND_BY)
    public ResponseEntity findByUsername(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        JmsReplyModel reply = null;

        if (username != null) {
            reply = this.userServices.findByUsername(username);
        } else if (email != null) {
            reply = this.userServices.findByEmail(email);
        }

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(UserRoutingConstants.ROLE_PROMOTE)
    public ResponseEntity rolePromote(@PathVariable String username) {
        JmsReplyModel reply = this.userServices.promote(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PostMapping(UserRoutingConstants.ROLE_DEMOTE)
    public ResponseEntity roleDemote(@PathVariable String username) {
        JmsReplyModel reply = this.userServices.demote(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.ROLE_HAS_NEXT)
    public ResponseEntity roleHasNext(@PathVariable String username) {
        JmsReplyModel reply = this.userServices.hasNextRole(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.ROLE_HAS_PREVIOUS)
    public ResponseEntity roleHasPrevious(@PathVariable String username) {
        JmsReplyModel reply = this.userServices.hasPreviousRole(username);

        return this.responseHelpers.respondGeneric(reply);
    }*/

}
