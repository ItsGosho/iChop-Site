package com.ichop.webstorage.controllers;

import com.ichop.webstorage.services.UserDataServices;
import com.ichop.webstorage.constants.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

    private final UserDataServices userDataServices;

    @Autowired
    public DataController(UserDataServices userDataServices) {
        this.userDataServices = userDataServices;
    }

    @GetMapping(value = URLConstants.GET_AVATAR,produces = "image/png")
    @ResponseBody
    public byte[] getAvatar(@PathVariable String username) {
        byte[] result = this.userDataServices.getAvatarAsBase64Array(username);
        return result;
    }
}
