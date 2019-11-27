package ichop.comments.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.comments.constants.CommentReplyConstants;
import ichop.comments.constants.CommentRoutingConstants;
import ichop.comments.domain.enums.Type;
import ichop.comments.domain.models.jms.UserProfileCommentReplyModel;
import ichop.comments.domain.models.jms.create.UserProfileCommentCreateRequest;
import ichop.comments.domain.models.jms.delete.CommentDeleteByIdRequest;
import ichop.comments.domain.models.service.UserProfileCommentServiceModel;
import ichop.comments.services.GenericCommentServices;
import ichop.comments.services.UserProfileCommentServices;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.requesters.UserRequester;
import org.ichop.commons.rest.helpers.ResponseHelpers;
import org.ichop.commons.validation.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserProfileCommentController {

    private final UserRequester userRequester;
    private final UserProfileCommentServices userProfileCommentServices;
    private final GenericCommentServices genericCommentServices;
    private final ResponseHelpers responseHelpers;
    private final ValidationHelper validationHelper;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserProfileCommentController(UserRequester userRequester,
                                        UserProfileCommentServices userProfileCommentServices,
                                        GenericCommentServices genericCommentServices, ResponseHelpers responseHelpers, ValidationHelper validationHelper, ObjectMapper objectMapper) {
        this.userRequester = userRequester;
        this.genericCommentServices = genericCommentServices;
        this.responseHelpers = responseHelpers;
        this.userProfileCommentServices = userProfileCommentServices;
        this.validationHelper = validationHelper;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(CommentRoutingConstants.USER_PROFILE_CREATE)
    public ResponseEntity create(@RequestBody UserProfileCommentCreateRequest request, Principal principal, @PathVariable String userProfileUsername) {
        request.setCreatorUsername(principal.getName());
        request.setUserProfileUsername(userProfileUsername);

        JmsReplyModel userReply = this.userRequester.findByUsername(request.getUserProfileUsername());

        if (userReply.isSuccessful()) {

            if (!this.validationHelper.isValid(request)) {
                String error = this.validationHelper.getValidationError(request);
                return this.responseHelpers.respondError(error);
            }

            UserProfileCommentServiceModel userProfileComment = this.objectMapper.convertValue(request, UserProfileCommentServiceModel.class);
            this.userProfileCommentServices.save(userProfileComment, UserProfileCommentReplyModel.class);

            return this.responseHelpers.respondSuccessful(CommentReplyConstants.COMMENT_CREATED_SUCCESSFUL);
        }

        return this.responseHelpers.respondGeneric(userReply);
    }

    @PreAuthorize("hasAuthority('MODERATOR') or @baseCommentRequesterImp.isCreator(#commentId,#principal.name,'USER_PROFILE') == true or #userProfileUsername.equals(#principal.name)")
    @PostMapping(CommentRoutingConstants.USER_PROFILE_DELETE)
    public ResponseEntity delete(@PathVariable String userProfileUsername, @PathVariable String commentId, Principal principal) {

        JmsReplyModel userReply = this.userRequester.findByUsername(userProfileUsername);

        if (userReply.isSuccessful()) {
            CommentDeleteByIdRequest request = new CommentDeleteByIdRequest();
            request.setId(commentId);
            request.setType(Type.USER_PROFILE);

            if (!this.validationHelper.isValid(request)) {
                String error = this.validationHelper.getValidationError(request);
                return this.responseHelpers.respondError(error);
            }

            this.genericCommentServices.deleteById(commentId, Type.USER_PROFILE);
            return this.responseHelpers.respondSuccessful(CommentReplyConstants.COMMENT_DELETE_SUCCESSFUL);
        }

        return this.responseHelpers.respondGeneric(userReply);
    }

    @GetMapping(CommentRoutingConstants.USER_PROFILE_ALL)
    public ResponseEntity findBy(@PathVariable String userProfileUsername) {

        JmsReplyModel userReply = this.userRequester.findByUsername(userProfileUsername);

        if (userReply.isSuccessful()) {
            List<UserProfileCommentServiceModel> result = this.userProfileCommentServices.findAllByUserProfileUsername(userProfileUsername);
            return this.responseHelpers.respondSuccessful(CommentReplyConstants.FETCH_SUCCESSFUL, result);
        }

        return this.responseHelpers.respondGeneric(userReply);
    }

}
