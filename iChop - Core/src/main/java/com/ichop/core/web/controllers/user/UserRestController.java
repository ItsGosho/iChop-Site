package com.ichop.core.web.controllers.user;

import com.google.gson.Gson;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.models.binding.user.UserForgottenPasswordBindingModel;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserRestController {

    private final UserServices userServices;

    @Autowired
    public UserRestController(UserServices userServices) {
        this.userServices = userServices;
    }


    @GetMapping(value = URLConstants.USER_EXISTS_GET, produces = "application/json")
    @ResponseBody
    public String isUserExists(@RequestParam(required = false) String username,
                               @RequestParam(required = false) String email) {
        if (username != null) {
            return new Gson().toJson(this.userServices.isUserExistsByUsername(username));
        } else if (email != null) {
            return new Gson().toJson(this.userServices.isUserExistsByEmail(email));
        }

        throw new IllegalArgumentException();
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = URLConstants.USER_SEND_EMAIL_RESET_PASSWORD_POST, produces = "application/json")
    @ResponseBody
    public String sendEmailForReset(@Valid UserForgottenPasswordBindingModel userForgottenPasswordBindingModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new Gson().toJson(false);
        }

        this.userServices.sendPasswordResetEmail(userForgottenPasswordBindingModel);

        return new Gson().toJson(true);
    }

    @GetMapping(value = URLConstants.IS_USER_FOLLOWING_USER, produces = "application/json")
    @ResponseBody
    public String isUserFollowing(@RequestParam(required = true) String user1Username,@RequestParam(required = true) String user2Username) {

        if(!this.userServices.isUserExistsByUsername(user1Username) || !this.userServices.isUserExistsByUsername(user2Username)){
            return new Gson().toJson(false);
        }

        UserServiceModel user1 = this.userServices.findUserByUsername(user1Username);
        UserServiceModel user2 = this.userServices.findUserByUsername(user2Username);

        boolean result = this.userServices.isUserAlreadyFollowedUser(user1,user2);
        return new Gson().toJson(result);
    }

}
