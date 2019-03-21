package com.ichop.core.web.controllers.user;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.exceptions.user.UserNotAuthorizedException;
import com.ichop.core.web.controllers.BaseController;
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
