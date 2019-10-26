package ichop.users.controllers;

import ichop.users.domain.models.binding.UserRegisterBinding;
import ichop.users.services.UserServices;
import ichop.users.common.validation.ValidationHelper;
import ichop.users.constants.URLConstants;
import ichop.users.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static ichop.users.constants.UserResponseConstants.REGISTRATION_SUCCESSFUL;

@RestController
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController {

    private final UserServices userServices;
    private final ValidationHelper validationHelper;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserAuthenticationController(UserServices userServices, ValidationHelper validationHelper, ResponseHelpers responseHelpers) {
        this.userServices = userServices;
        this.validationHelper = validationHelper;
        this.responseHelpers = responseHelpers;
    }


    @PostMapping(URLConstants.USER_REGISTER)
    public ResponseEntity proceedRegistration(UserRegisterBinding userRegisterBinding) {

        if (!this.validationHelper.isValid(userRegisterBinding)) {
            return this.responseHelpers.respondError(this.validationHelper.getValidationError(userRegisterBinding));
        }

        this.userServices.register(userRegisterBinding);
        return this.responseHelpers.respondSuccessful(REGISTRATION_SUCCESSFUL);
    }

}
