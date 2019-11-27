package ichop.core.areas.comment.controllers;

import ichop.core.areas.comment.constants.CommentRoutingConstants;
import ichop.core.areas.comment.models.CommentType;
import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateRequest;
import ichop.core.areas.comment.requesters.BaseCommentRequester;
import ichop.core.areas.comment.requesters.UserProfileCommentRequester;
import ichop.core.areas.rest.helpers.ResponseHelpers;
import ichop.core.areas.user.requesters.UserRequester;
import org.ichop.commons.domain.JmsReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserProfileCommentController {

    private final UserRequester userRequester;
    private final UserProfileCommentRequester userProfileCommentRequester;
    private final ResponseHelpers responseHelpers;
    private final BaseCommentRequester baseCommentRequester;

    @Autowired
    public UserProfileCommentController(UserRequester userRequester,
                                        UserProfileCommentRequester userProfileCommentRequester,
                                        ResponseHelpers responseHelpers,
                                        BaseCommentRequester baseCommentRequester) {
        this.userRequester = userRequester;
        this.userProfileCommentRequester = userProfileCommentRequester;
        this.responseHelpers = responseHelpers;
        this.baseCommentRequester = baseCommentRequester;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(CommentRoutingConstants.USER_PROFILE_CREATE)
    public ResponseEntity create(@RequestBody UserProfileCommentCreateRequest request, Principal principal, @PathVariable String userProfileUsername) {
        request.setCreatorUsername(principal.getName());
        request.setUserProfileUsername(userProfileUsername);

        JmsReplyModel userReply = this.userRequester.findByUsername(request.getUserProfileUsername());

        if (userReply.isSuccessful()) {
            JmsReplyModel result = this.userProfileCommentRequester.create(request);
            return this.responseHelpers.respondGeneric(result);
        }

        return this.responseHelpers.respondGeneric(userReply);
    }

    @PreAuthorize("hasAuthority('MODERATOR') or @baseCommentRequesterImp.isCreator(#commentId,#principal.name,'USER_PROFILE') == true or #userProfileUsername.equals(#principal.name)")
    @PostMapping(CommentRoutingConstants.USER_PROFILE_DELETE)
    public ResponseEntity delete(@PathVariable String userProfileUsername, @PathVariable String commentId, Principal principal) {

        JmsReplyModel userReply = this.userRequester.findByUsername(userProfileUsername);

        if (userReply.isSuccessful()) {
            JmsReplyModel result = this.baseCommentRequester.deleteById(commentId, CommentType.USER_PROFILE);
            return this.responseHelpers.respondGeneric(result);
        }

        return this.responseHelpers.respondGeneric(userReply);
    }

    @GetMapping(CommentRoutingConstants.USER_PROFILE_ALL)
    public ResponseEntity findBy(@PathVariable String userProfileUsername) {

        JmsReplyModel userReply = this.userRequester.findByUsername(userProfileUsername);

        if (userReply.isSuccessful()) {
            JmsReplyModel result = this.userProfileCommentRequester.findByUserProfileUsername(userProfileUsername);
            return this.responseHelpers.respondGeneric(result);
        }

        return this.responseHelpers.respondGeneric(userReply);
    }

}
