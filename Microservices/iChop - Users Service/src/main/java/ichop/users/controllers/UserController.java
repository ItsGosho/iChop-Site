package ichop.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.constants.UserRoutingConstants;
import ichop.users.domain.models.jms.password.change.UserChangePasswordRequest;
import ichop.users.domain.models.jms.retrieve.UserFindByUsernameRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.domain.models.view.UserViewModel;
import ichop.users.services.RoleServices;
import ichop.users.services.UserServices;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    private final UserServices userServices;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;
    private final ValidationHelper validationHelper;
    private final RoleServices roleServices;

    @Autowired
    public UserController(UserServices userServices,
                          ResponseHelpers responseHelpers,
                          ObjectMapper objectMapper,
                          ValidationHelper validationHelper, RoleServices roleServices) {
        this.userServices = userServices;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
        this.validationHelper = validationHelper;
        this.roleServices = roleServices;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD)
    public ResponseEntity changePassword(@RequestBody UserChangePasswordRequest changePasswordRequest, Principal principal) {
        changePasswordRequest.setUsername(principal.getName());

        if (!this.validationHelper.isValid(changePasswordRequest)) {
            String error = this.validationHelper.getValidationError(changePasswordRequest);
            return this.responseHelpers.respondError(error);
        }

        this.userServices.changePassword(changePasswordRequest.getUsername(), changePasswordRequest.getPassword());

        return this.responseHelpers.respondSuccessful("Password changed successful!");
    }

    /*@PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.FORGOTTEN_PASSWORD)
    public ResponseEntity forgottenPassword(@RequestBody UserForgottenPasswordRequest forgottenPasswordRequest) {
        JmsReplyModel reply = this.userRequester.forgottenPassword(forgottenPasswordRequest);

        return this.responseHelpers.respondGeneric(reply);
    }*/

    /*@PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD_BY_TOKEN)
    public ResponseEntity changePasswordByToken(@RequestBody UserChangePasswordByTokenRequest changePasswordByTokenRequest) {
        JmsReplyModel reply = this.userRequester.changePasswordByToken(changePasswordByTokenRequest);

        return this.responseHelpers.respondGeneric(reply);
    }*/

    @GetMapping(UserRoutingConstants.FIND_BY)
    public ResponseEntity findBy(@RequestParam String username) {
        UserFindByUsernameRequest request = new UserFindByUsernameRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        UserServiceModel user = this.userServices.findByUsername(username);
        UserViewModel viewModel = this.objectMapper.convertValue(user, UserViewModel.class);
        viewModel.setAuthority(this.roleServices.findHighestOfUser(user).getAuthority());

        return this.responseHelpers.respondSuccessful("Fetch successful!", viewModel);
    }

}
