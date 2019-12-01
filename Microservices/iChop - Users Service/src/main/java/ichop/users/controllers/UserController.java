package ichop.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.constants.UserReplyConstants;
import ichop.users.constants.UserRoutingConstants;
import ichop.users.domain.models.jms.email.EmailResetPasswordRequest;
import ichop.users.domain.models.jms.password.change.UserChangePasswordByTokenRequest;
import ichop.users.domain.models.jms.password.change.UserChangePasswordRequest;
import ichop.users.domain.models.jms.password.forgotten.UserForgottenPasswordRequest;
import ichop.users.domain.models.jms.retrieve.UserFindByUsernameRequest;
import ichop.users.domain.models.jms.token.PasswordTokenReply;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.domain.models.view.UserViewModel;
import ichop.users.requesters.EmailRequester;
import ichop.users.requesters.PasswordTokenRequester;
import ichop.users.services.RoleServices;
import ichop.users.services.UserServices;
import org.ichop.commons.domain.JmsReplyModel;
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
    private final EmailRequester emailRequester;
    private final PasswordTokenRequester passwordTokenRequester;

    @Autowired
    public UserController(UserServices userServices,
                          ResponseHelpers responseHelpers,
                          ObjectMapper objectMapper,
                          ValidationHelper validationHelper,
                          RoleServices roleServices,
                          EmailRequester emailRequester,
                          PasswordTokenRequester passwordTokenRequester) {
        this.userServices = userServices;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
        this.validationHelper = validationHelper;
        this.roleServices = roleServices;
        this.emailRequester = emailRequester;
        this.passwordTokenRequester = passwordTokenRequester;
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

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.FORGOTTEN_PASSWORD)
    public ResponseEntity forgottenPassword(@RequestBody UserForgottenPasswordRequest request) {

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        UserServiceModel user = this.userServices.findByEmail(request.getEmail());

        PasswordTokenCreateRequest passwordTokenCreateRequest = new PasswordTokenCreateRequest(user.getUsername());
        JmsReplyModel passwordTokenCreateReply = this.passwordTokenRequester.create(passwordTokenCreateRequest);
        PasswordTokenReply passwordToken = this.objectMapper.convertValue(passwordTokenCreateReply.getData(), PasswordTokenReply.class);

        EmailResetPasswordRequest emailResetPasswordRequest = new EmailResetPasswordRequest();
        emailResetPasswordRequest.setTo(user.getEmail());
        emailResetPasswordRequest.setToken(passwordToken.getToken());
        emailResetPasswordRequest.setExpirationDate(passwordToken.getCreationDate().plusHours(24));

        this.emailRequester.sendPasswordReset(emailResetPasswordRequest);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.PASSWORD_TOKEN_SENT_SUCCESSFUL);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD_BY_TOKEN)
    public ResponseEntity changePasswordByToken(@RequestBody UserChangePasswordByTokenRequest request) {

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        JmsReplyModel findByTokenReply = this.passwordTokenRequester.findByToken(request.getToken());
        PasswordTokenReply passwordToken = this.objectMapper.convertValue(findByTokenReply.getData(), PasswordTokenReply.class);

        UserServiceModel user = this.userServices.findByUsername(passwordToken.getUserUsername());

        this.userServices.changePassword(user.getUsername(), request.getPassword());

        return this.responseHelpers.respondSuccessful(UserReplyConstants.PASSWORD_CHANGED_SUCCESSFUL);
    }

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
