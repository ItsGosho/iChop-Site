package ichop.core.areas.user.web.controllers;

import ichop.core.constants.URLConstants;
import ichop.core.areas.user.exceptions.UserNotAuthorizedException;
import ichop.core.base.BaseController;
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
