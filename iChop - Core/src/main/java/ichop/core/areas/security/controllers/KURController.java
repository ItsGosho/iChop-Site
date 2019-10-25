package ichop.core.areas.security.controllers;

import ichop.core.areas.user.services.UserServices;
import ichop.core.common.validation.ValidationHelper;
import ichop.core.helpers.ResponseHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KURController {

    private final UserServices userServices;
    private final ValidationHelper validationHelper;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public KURController(UserServices userServices, ValidationHelper validationHelper, ResponseHelpers responseHelpers) {
        this.userServices = userServices;
        this.validationHelper = validationHelper;
        this.responseHelpers = responseHelpers;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/kur")
    public ResponseEntity proceedRegistration() {
        return this.responseHelpers.respondSuccessful("KURA IN");
    }

}
