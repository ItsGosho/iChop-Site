package ichop.web.controllers.user;

import ichop.constants.URLConstants;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.domain.models.view.user.UserProfileViewModel;
import ichop.service.user.UserServices;
import ichop.service.user.crud.UserCrudServices;
import ichop.service.user.view.UserViewServices;
import ichop.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController extends BaseController {

    private final UserCrudServices userCrudServices;
    private final UserServices userServices;
    private final UserViewServices userViewServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserCrudServices userCrudServices, UserServices userServices, UserViewServices userViewServices, ModelMapper modelMapper) {
        this.userCrudServices = userCrudServices;
        this.userServices = userServices;
        this.userViewServices = userViewServices;
        this.modelMapper = modelMapper;
    }


    @GetMapping(URLConstants.USER_PROFILE_GET)
    public ModelAndView getUserProfile(@PathVariable String username, ModelAndView modelAndView) {

        UserProfileViewModel user = this.userViewServices.getByUsername(username);

        modelAndView.addObject("user", user);

        return super.page("base-page", "user/user-profile", String.format("%s - Profile", user.getUsername()), modelAndView);
    }

    @GetMapping(URLConstants.USER_FOLLOW_POST)
    public String followUser(@PathVariable String username, Principal principal) {

        UserServiceModel user = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(),UserServiceModel.class);
        UserServiceModel userToFollow = this.userCrudServices.getUserByUsername(username);

        this.userServices.follow(user,userToFollow);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",userToFollow.getUsername());
        return super.redirect(redirectUrl);

    }

    @GetMapping(URLConstants.USER_UNFOLLOW_POST)
    public String unfollowUser(@PathVariable String username, Principal principal) {
        UserServiceModel user = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(),UserServiceModel.class);
        UserServiceModel userToUnfollow = this.userCrudServices.getUserByUsername(username);

        this.userServices.unfollow(user,userToUnfollow);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}",userToUnfollow.getUsername());
        return super.redirect(redirectUrl);

    }


}
