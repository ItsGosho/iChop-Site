package ichop.web.controllers.user;

import com.google.gson.Gson;
import ichop.constants.URLConstants;
import ichop.service.user.crud.UserCrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

    private final UserCrudServices userCrudServices;

    @Autowired
    public UserApiController(UserCrudServices userCrudServices) {
        this.userCrudServices = userCrudServices;
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

}
