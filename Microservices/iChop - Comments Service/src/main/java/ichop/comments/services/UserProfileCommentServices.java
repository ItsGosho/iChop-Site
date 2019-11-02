package ichop.comments.services;

import ichop.comments.domain.models.service.ThreadCommentServiceModel;
import ichop.comments.domain.models.service.UserProfileCommentServiceModel;

import java.util.List;

public interface UserProfileCommentServices extends CommentServices<UserProfileCommentServiceModel> {


    List<UserProfileCommentServiceModel> findAllByUserProfileUsername(String userProfileUsername);
}
