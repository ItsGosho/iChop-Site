package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.domain.models.binding.user.UserUpdateProfileInformationBindingModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.domain.models.view.user_options.UserProfileOptionsInformationViewModel;
import ichop.service.user.UserInformationServices;
import ichop.service.user.UserServices;
import ichop.service.user.view.UserViewServices;
import ichop.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserProfileOptionsController extends BaseController {

    private final UserServices userServices;
    private final UserInformationServices userInformationServices;
    private final UserViewServices userViewServices;

    @Autowired
    public UserProfileOptionsController(UserServices userServices, UserInformationServices userInformationServices, UserViewServices userViewServices) {
        this.userServices = userServices;
        this.userInformationServices = userInformationServices;
        this.userViewServices = userViewServices;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET)
    public ModelAndView profileOptionsInformationGet(Principal principal,ModelAndView modelAndView) {
        UserProfileOptionsInformationViewModel userInfo = this.userViewServices.getUserProfileOptionsInformationViewModel(principal.getName());

        modelAndView.addObject("optionsPage","user/options/user-options-profile-information");
        modelAndView.addObject("userInfo",userInfo);

        return super.page("user/options/user-options-base","Options",modelAndView);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_POST)
    public String profileOptionsInformationUpdate(@Valid UserUpdateProfileInformationBindingModel userUpdateProfileInformationBindingModel, BindingResult bindingResult,Principal principal) {

        if(bindingResult.hasErrors()){
            return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
        }

        UserServiceModel userServiceModel = this.userServices.findUserByUsername(principal.getName());
        this.userInformationServices.update(userUpdateProfileInformationBindingModel,userServiceModel);

        return super.redirect(URLConstants.USER_PROFILE_OPTIONS_INFORMATION_GET);
    }

}
