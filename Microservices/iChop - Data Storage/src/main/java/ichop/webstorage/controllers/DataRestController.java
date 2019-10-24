package ichop.webstorage.controllers;

import ichop.webstorage.services.UserDataServices;
import ichop.webstorage.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataRestController {

    private final UserDataServices userDataServices;

    @Autowired
    public DataRestController(UserDataServices userDataServices) {
        this.userDataServices = userDataServices;
    }

    @GetMapping(value = URLConstants.GET_AVATAR, produces = "image/png")
    @ResponseBody
    public byte[] getAvatar(@PathVariable String username) {
        return this.userDataServices.getAvatarAsBase64Array(username);
    }
}
