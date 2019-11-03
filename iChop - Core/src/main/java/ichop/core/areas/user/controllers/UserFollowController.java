package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.jms.follow.*;
import ichop.core.areas.user.requester.UserFollowRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserFollowController {

    private final UserFollowRequester userFollowRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserFollowController(UserFollowRequester userFollowRequester, ResponseHelpers responseHelpers) {
        this.userFollowRequester = userFollowRequester;
        this.responseHelpers = responseHelpers;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.FOLLOW)
    public ResponseEntity follow(@PathVariable(name = "username") String userToFollowUsername, Principal principal) {

        JmsReplyModel reply = this.userFollowRequester.follow(principal.getName(), userToFollowUsername);

        return this.responseHelpers.respondGeneric(reply);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.UNFOLLOW)
    public ResponseEntity unfollow(@PathVariable(name = "username") String userToUnfollowUsername, Principal principal) {

        JmsReplyModel reply = this.userFollowRequester.unfollow(principal.getName(), userToUnfollowUsername);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.ALL_FOLLOWERS)
    public ResponseEntity allFollowers(@PathVariable String username) {

        JmsReplyModel reply = this.userFollowRequester.allFollowers(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.ALL_FOLLOWINGS)
    public ResponseEntity allFollowings(@PathVariable String username) {

        JmsReplyModel reply = this.userFollowRequester.allFollowings(username);

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.IS_FOLLOWING)
    public ResponseEntity allFollowings(@PathVariable String username, @RequestParam(name = "username") String follow) {

        JmsReplyModel reply = this.userFollowRequester.isFollowing(username, follow);

        return this.responseHelpers.respondGeneric(reply);
    }

}
