package itsgosho.controllers;

import itsgosho.constants.URLConstants;
import itsgosho.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

    private final UserServices userServices;

    @Autowired
    public DataController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping(value = URLConstants.GET_AVATAR,produces = "image/png")
    @ResponseBody
    public byte[] getAvatar(@PathVariable String username) {
        byte[] result = this.userServices.getAvatarAsBase64Array(username);
        return result;
    }
}
