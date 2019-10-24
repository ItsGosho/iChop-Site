package ichop.core.areas.user.web.controllers;

import ichop.core.areas.user.domain.models.binding.UserRegisterBindingModel;
import ichop.core.areas.user.services.UserServices;
import ichop.core.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@PreAuthorize("isAnonymous()")
public class UserAuthenticationController {

    private final UserServices userServices;

    @Autowired
    public UserAuthenticationController(UserServices userServices) {
        this.userServices = userServices;
    }


    @PostMapping(URLConstants.USER_REGISTER_POST)
    public ResponseEntity proceedRegistration(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        this.userServices.register(userRegisterBindingModel);
        //this.userWebStorageJmsServices.sendUpdateAvatarRequestWithInitialImage(userRegisterBindingModel.getUsername());
        return new ResponseEntity<>("",HttpStatus.OK);
    }

}
