package ichop.core.areas.user.web.controllers;

import ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.common.validation.ValidationHelper;
import ichop.core.constants.URLConstants;
import ichop.core.filters.JwtAuthorizationFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController {

    private static final Logger LOG = LogManager.getLogger(JwtAuthorizationFilter.class);

    private final UserServices userServices;
    private final ValidationHelper validationHelper;

    @Autowired
    public UserAuthenticationController(UserServices userServices, ValidationHelper validationHelper) {
        this.userServices = userServices;
        this.validationHelper = validationHelper;
    }


    @PostMapping(URLConstants.USER_REGISTER)
    public ResponseEntity proceedRegistration(UserRegisterBindingModel userRegisterBindingModel) {

        if (!this.validationHelper.isValid(userRegisterBindingModel)) {
            return new ResponseEntity<>(this.validationHelper.getValidationError(userRegisterBindingModel), HttpStatus.BAD_REQUEST);
        }

        this.userServices.register(userRegisterBindingModel);
        //this.userWebStorageJmsServices.sendUpdateAvatarRequestWithInitialImage(userRegisterBindingModel.getUsername());
        return new ResponseEntity<>("Successful registration!", HttpStatus.OK);
    }

}
