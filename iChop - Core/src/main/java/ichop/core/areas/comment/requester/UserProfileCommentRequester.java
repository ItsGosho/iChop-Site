package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.UserProfileCommentReplyModel;
import ichop.core.areas.comment.models.jms.all.UserProfileCommentsByUserProfileUsernameRequest;
import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateRequest;
import ichop.core.common.domain.EmptyReplyModel;

public interface UserProfileCommentRequester {

    UserProfileCommentReplyModel create(UserProfileCommentCreateRequest request);
    EmptyReplyModel deleteById(String id);
    UserProfileCommentReplyModel findByUserProfileUsername(UserProfileCommentsByUserProfileUsernameRequest request);

}
