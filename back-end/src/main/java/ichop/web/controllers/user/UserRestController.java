package ichop.web.controllers.user;

import com.google.gson.Gson;
import ichop.constants.URLConstants;
import ichop.domain.models.binding.user.UserForgottenPasswordBindingModel;
import ichop.domain.models.view.user.UsersAllViewModel;
import ichop.service.user.UserServices;
import ichop.service.user.crud.UserCrudServices;
import ichop.service.user.view.UserViewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRestController {

    private final UserServices userServices;
    private final UserCrudServices userCrudServices;
    private final UserViewServices userViewServices;

    @Autowired
    public UserRestController(UserServices userServices, UserCrudServices userCrudServices, UserViewServices userViewServices) {
        this.userServices = userServices;
        this.userCrudServices = userCrudServices;
        this.userViewServices = userViewServices;
    }


    @GetMapping(value = URLConstants.USER_EXISTS_GET, produces = "application/json")
    @ResponseBody
    public String isUserExists(@RequestParam(required = false) String username,
                               @RequestParam(required = false) String email) {
        if (username != null) {
            return new Gson().toJson(this.userCrudServices.existsByUsername(username));
        } else if (email != null) {
            return new Gson().toJson(this.userCrudServices.existsByEmail(email));
        }

        throw new IllegalArgumentException();
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = URLConstants.USER_SEND_EMAIL_RESET_PASSWORD_POST,produces = "application/json")
    @ResponseBody
    public String sendEmailForReset(@Valid UserForgottenPasswordBindingModel userForgottenPasswordBindingModel, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new Gson().toJson(false);
        }

        this.userServices.sendPasswordResetEmail(userForgottenPasswordBindingModel);

        return new Gson().toJson(true);
    }

}
