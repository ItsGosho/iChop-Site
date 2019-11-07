package ichop.authentication.controller;

import ichop.authentication.domain.models.web.LoginBindingModel;
import ichop.authentication.domain.models.web.LoginResponseModel;
import ichop.authentication.domain.models.web.RegisterBindingModel;
import ichop.authentication.domain.models.web.RegisterResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public ResponseEntity login(LoginBindingModel loginBindingModel) {


        LoginResponseModel loginResponseModel = new LoginResponseModel();
        return new ResponseEntity<>(loginResponseModel, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Kura')")
    @GetMapping("/register")
    public ResponseEntity register(@ModelAttribute RegisterBindingModel registerBindingModel) {


        RegisterResponseModel registerResponseModel = new RegisterResponseModel();
        return new ResponseEntity<>(registerResponseModel, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/authenticated")
    public ResponseEntity authenticated() {
        return new ResponseEntity<>("Authenticated!", HttpStatus.OK);
    }

}
