package ichop.core.areas.security.controllers;

import ichop.core.areas.user.domain.models.binding.UserRegisterBinding;
import ichop.core.areas.user.services.UserServices;
import ichop.core.common.validation.ValidationHelper;
import ichop.core.constants.URLConstants;
import ichop.core.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static ichop.core.areas.user.constants.UserResponseConstants.REGISTRATION_SUCCESSFUL;

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
