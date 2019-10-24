package ichop.core.areas.user.web.controllers;

import ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.common.validation.ValidationHelper;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController {

    private final UserServices userServices;
    private final ValidationHelper validationHelper;

    @Autowired
    public UserAuthenticationController(UserServices userServices, ValidationHelper validationHelper) {
        this.userServices = userServices;
        this.validationHelper = validationHelper;
    }


    @PostMapping(URLConstants.USER_REGISTER_POST)
    public ResponseEntity proceedRegistration(UserRegisterBindingModel userRegisterBindingModel) {

        if (!this.validationHelper.isValid(userRegisterBindingModel)) {
            return new ResponseEntity<>(this.validationHelper.getValidationError(userRegisterBindingModel), HttpStatus.BAD_REQUEST);
        }

        this.userServices.register(userRegisterBindingModel);
        //this.userWebStorageJmsServices.sendUpdateAvatarRequestWithInitialImage(userRegisterBindingModel.getUsername());
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
