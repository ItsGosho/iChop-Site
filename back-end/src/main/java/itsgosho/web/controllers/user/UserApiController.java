package itsgosho.web.controllers.user;

import com.google.gson.Gson;
import itsgosho.constants.URLConstants;
import itsgosho.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

    private final UserServices userServices;

    @Autowired
    public UserApiController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(value = URLConstants.USER_EXISTS_GET, produces = "application/json")
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
