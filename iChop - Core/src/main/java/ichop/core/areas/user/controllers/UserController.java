package ichop.core.areas.user.controllers;

import ichop.core.areas.user.constants.UserRoutingConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD)
    private ResponseEntity changePassword() {

        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.FORGOTTEN_PASSWORD)
    private ResponseEntity forgottenPassword() {

        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.CHANGE_PASSWORD_BY_TOKEN)
    private ResponseEntity changePasswordByToken() {

        return null;
    }
}
