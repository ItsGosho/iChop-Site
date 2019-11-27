package ichop.users.controllers;

import ichop.users.constants.UserReplyConstants;
import ichop.users.constants.UserRoutingConstants;
import ichop.users.domain.models.jms.role.UserHasNextRoleRequest;
import ichop.users.domain.models.jms.role.UserHasPreviousRoleRequest;
import ichop.users.domain.models.jms.role.UserRoleDemoteRequest;
import ichop.users.domain.models.jms.role.UserRolePromoteRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.services.UserServices;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class UserAdminController {

    private final UserServices userServices;
    private final ResponseHelpers responseHelpers;
    private final ValidationHelper validationHelper;

    @Autowired
    public UserAdminController(UserServices userServices, ResponseHelpers responseHelpers, ValidationHelper validationHelper) {
        this.userServices = userServices;
        this.responseHelpers = responseHelpers;
        this.validationHelper = validationHelper;
    }

    @GetMapping(UserRoutingConstants.ADMIN_FIND_BY)
    public ResponseEntity findByUsername(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {

        if (this.userServices.existsByUsername(username)) {
            UserServiceModel user = this.userServices.findByUsername(username);
            return this.responseHelpers.respondSuccessful(UserReplyConstants.FETCHED_SUCCESSFUL, user);
        } else if (this.userServices.existsByEmail(email)) {
            UserServiceModel user = this.userServices.findByEmail(email);
            return this.responseHelpers.respondSuccessful(UserReplyConstants.FETCHED_SUCCESSFUL, user);
        }

        return this.responseHelpers.respondError(UserReplyConstants.NOT_FOUND);
    }

    @PostMapping(UserRoutingConstants.ROLE_PROMOTE)
    public ResponseEntity rolePromote(@PathVariable String username) {
        UserRolePromoteRequest request = new UserRolePromoteRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        this.userServices.promote(username);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.PROMOTE_SUCCESSFUL);
    }

    @PostMapping(UserRoutingConstants.ROLE_DEMOTE)
    public ResponseEntity roleDemote(@PathVariable String username) {
        UserRoleDemoteRequest request = new UserRoleDemoteRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        this.userServices.demote(username);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.DEMOTE_SUCCESSFUL);
    }

    @GetMapping(UserRoutingConstants.ROLE_HAS_NEXT)
    public ResponseEntity roleHasNext(@PathVariable String username) {
        UserHasNextRoleRequest request = new UserHasNextRoleRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        boolean hasNext = this.userServices.hasNextRole(username);
        BoolReply boolReply = new BoolReply(hasNext);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.FETCHED_SUCCESSFUL, boolReply);
    }

    @GetMapping(UserRoutingConstants.ROLE_HAS_PREVIOUS)
    public ResponseEntity roleHasPrevious(@PathVariable String username) {
        UserHasPreviousRoleRequest request = new UserHasPreviousRoleRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        boolean hasPrevious = this.userServices.hasPreviousRole(username);
        BoolReply boolReply = new BoolReply(hasPrevious);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.FETCHED_SUCCESSFUL, boolReply);
    }

}
