package ichop.authentication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/guest")
    public ResponseEntity guest() {
        return new ResponseEntity<>("Guest!", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/authenticated")
    public ResponseEntity authenticated() {
        return new ResponseEntity<>("Authenticated!", HttpStatus.OK);
    }

}
