package ichop.core.areas.comment.controllers;

import ichop.core.areas.comment.constants.CommentRoutingConstants;
import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateRequest;
import ichop.core.areas.comment.requester.UserProfileCommentRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserProfileCommentController {

    private final UserProfileCommentRequester userProfileCommentRequester;

    @Autowired
    public UserProfileCommentController(UserProfileCommentRequester userProfileCommentRequester) {
        this.userProfileCommentRequester = userProfileCommentRequester;
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping(CommentRoutingConstants.USER_PROFILE_CREATE)
    public ResponseEntity create(UserProfileCommentCreateRequest request, Principal principal) {

        return null;
    }

    @PreAuthorize("hasAuthority('MODERATOR') and baseCommentRequesterImp.isCreator(#id,#principal.name,'USER_PROFILE') == true")
    @PostMapping(CommentRoutingConstants.USER_PROFILE_DELETE)
    public ResponseEntity delete(@PathVariable String id, Principal principal) {

        return null;
    }

    @PostMapping(CommentRoutingConstants.USER_PROFILE_ALL)
    public ResponseEntity findBy(@RequestParam String userProfileUsername) {

        return null;
    }
}
