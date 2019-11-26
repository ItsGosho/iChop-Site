package ichop.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.constants.UserReplyConstants;
import ichop.users.constants.UserRoutingConstants;
import ichop.users.domain.models.jms.follow.*;
import ichop.users.domain.models.service.UserServiceModel;
import ichop.users.domain.models.view.UserViewModel;
import ichop.users.services.UserFollowServices;
import ichop.users.services.UserServices;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserFollowController {

    private final UserServices userServices;
    private final UserFollowServices userFollowServices;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;
    private final ValidationHelper validationHelper;

    @Autowired
    public UserFollowController(UserServices userServices,
                                UserFollowServices userFollowServices,
                                ResponseHelpers responseHelpers,
                                ObjectMapper objectMapper,
                                ValidationHelper validationHelper) {
        this.userServices = userServices;
        this.userFollowServices = userFollowServices;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
        this.validationHelper = validationHelper;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.FOLLOW)
    public ResponseEntity follow(@PathVariable(name = "username") String userToFollowUsername, Principal principal) {
        UserFollowRequest request = new UserFollowRequest();
        request.setUsername(principal.getName());
        request.setFollowUsername(userToFollowUsername);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }


        UserServiceModel user = this.userServices.findByUsername(request.getUsername());
        UserServiceModel follow = this.userServices.findByUsername(request.getFollowUsername());

        this.userFollowServices.follow(user, follow);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.FOLLOW_SUCCESSFUL);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.UNFOLLOW)
    public ResponseEntity unfollow(@PathVariable(name = "username") String userToUnfollowUsername, Principal principal) {
        UserUnfollowRequest request = new UserUnfollowRequest();
        request.setUsername(principal.getName());
        request.setUnfollowUsername(userToUnfollowUsername);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        UserServiceModel user = this.userServices.findByUsername(request.getUsername());
        UserServiceModel unfollow = this.userServices.findByUsername(request.getUnfollowUsername());

        this.userFollowServices.unfollow(user, unfollow);

        return this.responseHelpers.respondSuccessful(UserReplyConstants.UNFOLLOW_SUCCESSFUL);
    }

    /*TODO: refactor it*/
    @GetMapping(UserRoutingConstants.ALL_FOLLOWERS)
    public ResponseEntity allFollowers(@PathVariable String username) {
        UserFollowersAllRequest request = new UserFollowersAllRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        UserServiceModel user = this.userServices.findByUsername(request.getUsername());
        List<UserViewModel> followers = this.userFollowServices.getFollowers(user)
                .stream()
                .map(x -> this.objectMapper.convertValue(x, UserViewModel.class))
                .collect(Collectors.toList());

        return this.responseHelpers.respondSuccessful("Fetch successful!", followers);
    }

    /*TODO: refactor it*/
    @GetMapping(UserRoutingConstants.ALL_FOLLOWINGS)
    public ResponseEntity allFollowings(@PathVariable String username) {
        UserFollowingsAllRequest request = new UserFollowingsAllRequest();
        request.setUsername(username);

        if (!this.validationHelper.isValid(request)) {
            String error = this.validationHelper.getValidationError(request);
            return this.responseHelpers.respondError(error);
        }

        UserServiceModel user = this.userServices.findByUsername(request.getUsername());
        List<UserViewModel> followers = this.userFollowServices.getFollowings(user)
                .stream()
                .map(x -> this.objectMapper.convertValue(x, UserViewModel.class))
                .collect(Collectors.toList());

        return this.responseHelpers.respondSuccessful("Fetch successful!", followers);
    }

    @GetMapping(UserRoutingConstants.IS_FOLLOWING)
    public ResponseEntity allFollowings(@PathVariable String username, @RequestParam(name = "username") String follow) {
        boolean result = this.userFollowServices.isFollowed(username, follow);
        BoolReply boolReply = new BoolReply(result);

        return this.responseHelpers.respondSuccessful("Fetch successful!", boolReply);
    }

}
