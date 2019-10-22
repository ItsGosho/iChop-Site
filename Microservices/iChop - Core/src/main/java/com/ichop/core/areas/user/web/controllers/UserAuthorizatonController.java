package com.ichop.core.areas.user.web.controllers;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.areas.user.exceptions.UserNotAuthorizedException;
import com.ichop.core.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAuthorizatonController extends BaseController {

    @GetMapping(URLConstants.UNAUTHORIZED)
    public ModelAndView noPermission(){
        throw new UserNotAuthorizedException();
    }
}
