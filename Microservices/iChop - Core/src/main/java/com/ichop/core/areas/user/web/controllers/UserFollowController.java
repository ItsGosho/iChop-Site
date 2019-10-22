package com.ichop.core.areas.user.web.controllers;

import com.google.gson.Gson;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.domain.models.view.user_follow_all.UserFollowAllViewModel;
import com.ichop.core.areas.user.helpers.view.user_follow_all.UserFollowAllViewHelper;
import com.ichop.core.areas.user.services.UserFollowServices;
import com.ichop.core.areas.user.services.UserServices;
import com.ichop.core.base.BaseController;
import com.ichop.core.constants.URLConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class UserFollowController extends BaseController {

    private final UserServices userServices;
    private final UserFollowServices userFollowServices;
    private final ModelMapper modelMapper;
    private final UserFollowAllViewHelper userFollowAllViewHelper;

    @Autowired
    public UserFollowController(UserServices userServices, UserFollowServices userFollowServices, ModelMapper modelMapper, UserFollowAllViewHelper userFollowAllViewHelper) {
        this.userServices = userServices;
        this.userFollowServices = userFollowServices;
        this.modelMapper = modelMapper;
        this.userFollowAllViewHelper = userFollowAllViewHelper;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_FOLLOW_POST)
    public String proceedUserFollow(@PathVariable String username, Principal principal) {

        UserServiceModel user = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);
        UserServiceModel userToFollow = this.userServices.findUserByUsername(username);

        this.userFollowServices.follow(user,userToFollow);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}", userToFollow.getUsername());
        return super.redirect(redirectUrl);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(URLConstants.USER_UNFOLLOW_POST)
    public String proceedUserUnfollow(@PathVariable String username, Principal principal) {

        UserServiceModel user = this.modelMapper.map(((Authentication) principal).getPrincipal(), UserServiceModel.class);
        UserServiceModel userToUnfollow = this.userServices.findUserByUsername(username);

        this.userFollowServices.unfollow(user,userToUnfollow);

        String redirectUrl = URLConstants.USER_PROFILE_GET.replace("{username}", userToUnfollow.getUsername());
        return super.redirect(redirectUrl);

    }

    @GetMapping(value = URLConstants.USER_ALL_FOLLOWINGS,produces = "application/json")
    @ResponseBody
    public String returnAllFollowings(@PathVariable String username) {
        List<UserFollowAllViewModel> result = this.userFollowAllViewHelper.createFollowings(username);
        return new Gson().toJson(result);
    }

    @GetMapping(value = URLConstants.USER_ALL_FOLLOWERS,produces = "application/json")
    @ResponseBody
    public String returnAllFollowers(@PathVariable String username) {
        List<UserFollowAllViewModel> result = this.userFollowAllViewHelper.createFollowers(username);
        return new Gson().toJson(result);

    }

    @GetMapping(value = URLConstants.IS_USER_FOLLOWING_USER, produces = "application/json")
    @ResponseBody
    public String isUserFollowingAnotherUser(@RequestParam String user1Username, @RequestParam String user2Username) {

        if(!this.userServices.isUserExistsByUsername(user1Username) || !this.userServices.isUserExistsByUsername(user2Username)){
            return new Gson().toJson(false);
        }

        UserServiceModel user1 = this.userServices.findUserByUsername(user1Username);
        UserServiceModel user2 = this.userServices.findUserByUsername(user2Username);

        boolean result = this.userFollowServices.isUserAlreadyFollowedUser(user1,user2);
        return new Gson().toJson(result);
    }

}
