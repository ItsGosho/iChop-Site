package itsgosho.web.controllers;

import com.google.gson.Gson;
import itsgosho.domain.models.binding.UserForgottenPasswordBindingModel;
import itsgosho.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/mail/reset/password",produces = "application/json")
    @ResponseBody
    public String sendEmailForReset(@Valid UserForgottenPasswordBindingModel userForgottenPasswordBindingModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Gson().toJson(false);
        }

        if(!this.userServices.sendPasswordResetEmail(userForgottenPasswordBindingModel)){
            return new Gson().toJson(false);
        }

        return new Gson().toJson(true);
    }
}
