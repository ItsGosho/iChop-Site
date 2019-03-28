package com.ichop.core.web.controllers.user;

import com.ichop.core.constants.URLConstants;
import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.models.service.user.UserServiceModel;
import com.ichop.core.domain.models.view.user_all.UsersAllViewModel;
import com.ichop.core.domain.models.view.user_profile.UserProfileViewModel;
import com.ichop.core.helpers.view.user_all.UsersAllViewHelper;
import com.ichop.core.helpers.view.user_profile.UserProfileViewHelper;
import com.ichop.core.service.user.UserServices;
import com.ichop.core.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController extends BaseController {

    private final UserServices userServices;
    private final UsersAllViewHelper usersAllViewHelper;
    private final ModelMapper modelMapper;
    private final UserProfileViewHelper userProfileViewHelper;

    @Autowired
    public UserController(UserServices userServices, UsersAllViewHelper usersAllViewHelper, ModelMapper modelMapper, UserProfileViewHelper userProfileViewHelper) {
        this.userServices = userServices;
        this.usersAllViewHelper = usersAllViewHelper;
        this.modelMapper = modelMapper;
        this.userProfileViewHelper = userProfileViewHelper;
    }


    @GetMapping(URLConstants.USER_PROFILE_GET)
    public ModelAndView getUserProfile(@PathVariable String username, ModelAndView modelAndView) {

        UserProfileViewModel user = this.userProfileViewHelper.create(username);

        modelAndView.addObject("user", user);

        return super.page("user/user-profile", String.format("%s`s profile", user.getUsername()), modelAndView);
    }

    @GetMapping(URLConstants.USER_FOLLOW_POST)
    public String followUser(@PathVariable String username, Principal principal) {

        UserServiceModel user = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);
        UserServiceModel userToFollow = this.userServices.findUserByUsername(username);

        this.userServices.follow(user, userToFollow);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}", userToFollow.getUsername());
        return super.redirect(redirectUrl);

    }

    @GetMapping(URLConstants.USER_UNFOLLOW_POST)
    public String unfollowUser(@PathVariable String username, Principal principal) {
        UserServiceModel user = this.modelMapper.map((User) ((Authentication) principal).getPrincipal(), UserServiceModel.class);
        UserServiceModel userToUnfollow = this.userServices.findUserByUsername(username);

        this.userServices.unfollow(user, userToUnfollow);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}", userToUnfollow.getUsername());
        return super.redirect(redirectUrl);

    }

    @GetMapping(URLConstants.USER_ALL_GET)
    public ModelAndView allUsers(ModelAndView modelAndView,
                                 @PageableDefault(size = 10, sort = "registrationDate", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(value = "isUsernameLike",required = false) String isUsernameLike,
                                 @RequestParam(value = "hasRole",required = false) String hasRole) {


        Page<UsersAllViewModel> users;
        if(isUsernameLike != null) {
            users = this.usersAllViewHelper.createWhereUsernameContains(isUsernameLike, pageable);
        }else if(hasRole != null){
            users = this.usersAllViewHelper.createWhereRoleIsPresent(hasRole, pageable);
        }else{
            users = this.usersAllViewHelper.create(pageable);
        }

        modelAndView.addObject("users",users);

        return super.page("user/users-all", "All Users", modelAndView);
    }


}
