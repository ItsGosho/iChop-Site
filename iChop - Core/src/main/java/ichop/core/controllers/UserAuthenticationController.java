package ichop.core.controllers;

import ichop.core.common.validation.ValidationHelper;
import ichop.core.constants.URLConstants;
import ichop.core.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController {

    private final ValidationHelper validationHelper;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserAuthenticationController(ValidationHelper validationHelper, ResponseHelpers responseHelpers) {
        this.validationHelper = validationHelper;
        this.responseHelpers = responseHelpers;
    }


    @PostMapping(URLConstants.USER_REGISTER)
    public ResponseEntity proceedRegistration() {
        /*TODO:*/
        return null;
    }

}
