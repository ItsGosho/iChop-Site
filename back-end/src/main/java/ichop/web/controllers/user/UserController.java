package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.web.controllers.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {



    //@PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_GET)
    public ModelAndView getUserProfile(@PathVariable String id,ModelAndView modelAndView){

        return super.page("base-page","information/user-profile","USERNAME",modelAndView);
    }

}
