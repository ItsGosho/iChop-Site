package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import ichop.service.user.UserServices;
import ichop.service.user.view.UserViewServices;
import ichop.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserProfileOptionsController extends BaseController {

    private final UserServices userServices;
    private final UserViewServices userViewServices;

    @Autowired
    public UserProfileOptionsController(UserServices userServices, UserViewServices userViewServices) {
        this.userServices = userServices;
        this.userViewServices = userViewServices;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET)
    public ModelAndView profileOptionsInformationGet(Principal principal,ModelAndView modelAndView) {
        UserProfileOptionsInformationViewModel userInfo = this.userViewServices.getUserProfileOptionsInformationViewModel(principal.getName());

        modelAndView.addObject("optionsPage","user/options/user-options-profile-information");
        modelAndView.addObject("userInfo",userInfo);

        return super.page("user/options/user-options-base","Information",modelAndView);
    }

}
