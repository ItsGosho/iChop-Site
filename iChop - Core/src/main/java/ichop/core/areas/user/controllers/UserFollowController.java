package ichop.core.areas.user.controllers;

import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.requester.UserRequester;
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

    private final UserRequester userRequester;
    private final ResponseHelpers responseHelpers;

    @Autowired
    public UserFollowController(UserRequester userRequester, ResponseHelpers responseHelpers) {
        this.userRequester = userRequester;
        this.responseHelpers = responseHelpers;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.FOLLOW)
    public ResponseEntity follow(@PathVariable(name = "username") String userToFollowUsername, Principal principal) {

        return this.responseHelpers.respondGeneric(null);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(UserRoutingConstants.UNFOLLOW)
    public ResponseEntity unfollow(@PathVariable(name = "username") String userToUnfollowUsername, Principal principal) {


        return this.responseHelpers.respondGeneric(null);
    }

    @GetMapping(UserRoutingConstants.ALL_FOLLOWERS)
    public ResponseEntity allFollowers(@PathVariable String username) {

        return this.responseHelpers.respondGeneric(null);
    }

    @GetMapping(UserRoutingConstants.ALL_FOLLOWINGS)
    public ResponseEntity allFollowings(@PathVariable String username) {

        return this.responseHelpers.respondGeneric(null);
    }

    @GetMapping(UserRoutingConstants.IS_FOLLOWING)
    public ResponseEntity allFollowings(@PathVariable String username, @RequestParam(name = "username") String follow) {

        return this.responseHelpers.respondGeneric(null);
    }

}
