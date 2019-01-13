package itsgosho.web.controllers;

import com.google.gson.Gson;
import itsgosho.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserServices userServices;

    @Autowired
    public UserRestController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(value = "/exists", produces = "application/json")
    @ResponseBody
    public String isUserExists(@RequestParam(required = false) String username,
                                               @RequestParam(required = false) String email) {
        if (username != null) {
            return new Gson().toJson(this.userServices.existsByUsername(username));
        } else if (email != null) {
            return new Gson().toJson(this.userServices.existsByEmail(email));
        }
        throw new IllegalArgumentException();
    }
}
