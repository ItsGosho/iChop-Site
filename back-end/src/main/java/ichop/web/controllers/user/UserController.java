package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.domain.models.view.user.profile.UserProfileViewModel;
import ichop.service.user.view.UserViewServices;
import ichop.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseController {

    private final UserViewServices userViewServices;

    @Autowired
    public UserController(UserViewServices userViewServices) {
        this.userViewServices = userViewServices;
    }


    @GetMapping(URLConstants.USER_PROFILE_GET)
    public ModelAndView getUserProfile(@PathVariable String username,ModelAndView modelAndView){

        UserProfileViewModel user = this.userViewServices.getByUsername(username);

        modelAndView.addObject("user",user);

        return super.page("base-page","information/user-profile",String.format("%s - Profile",user.getUsername()),modelAndView);
    }

}
