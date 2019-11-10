package ichop.core.areas.user.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.constants.UserRoutingConstants;
import ichop.core.areas.user.models.view.UserViewModel;
import ichop.core.areas.user.requesters.UserFollowRequester;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserFollowController {

    private final UserFollowRequester userFollowRequester;
    private final ResponseHelpers responseHelpers;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserFollowController(UserFollowRequester userFollowRequester,
                                ResponseHelpers responseHelpers,
                                ObjectMapper objectMapper) {
        this.userFollowRequester = userFollowRequester;
        this.responseHelpers = responseHelpers;
        this.objectMapper = objectMapper;
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

    /*TODO: refactor it*/
    @GetMapping(UserRoutingConstants.ALL_FOLLOWERS)
    public ResponseEntity allFollowers(@PathVariable String username) {

        JmsReplyModel reply = this.userFollowRequester.allFollowers(username);

        if (reply.isSuccessful()) {
            List<UserViewModel> data = new ArrayList<>();
            List<Object> wtf = (List<Object>) reply.getData();

            for (Object item : wtf) {
                UserViewModel viewModel = this.objectMapper.convertValue(item, UserViewModel.class);
                data.add(viewModel);
            }
            reply.setData(data);
        }

        return this.responseHelpers.respondGeneric(reply);
    }

    /*TODO: refactor it*/
    @GetMapping(UserRoutingConstants.ALL_FOLLOWINGS)
    public ResponseEntity allFollowings(@PathVariable String username) {

        JmsReplyModel reply = this.userFollowRequester.allFollowings(username);

        if (reply.isSuccessful()) {
            List<UserViewModel> data = new ArrayList<>();
            List<Object> wtf = (List<Object>) reply.getData();

            for (Object item : wtf) {
                UserViewModel viewModel = this.objectMapper.convertValue(item, UserViewModel.class);
                data.add(viewModel);
            }
            reply.setData(data);
        }

        return this.responseHelpers.respondGeneric(reply);
    }

    @GetMapping(UserRoutingConstants.IS_FOLLOWING)
    public ResponseEntity allFollowings(@PathVariable String username, @RequestParam(name = "username") String follow) {

        JmsReplyModel reply = this.userFollowRequester.isFollowing(username, follow);

        return this.responseHelpers.respondGeneric(reply);
    }

}
