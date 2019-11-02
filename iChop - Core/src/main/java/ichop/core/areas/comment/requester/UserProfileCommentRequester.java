package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.all.UserProfileCommentsByUserProfileUsernameReply;
import ichop.core.areas.comment.models.jms.all.UserProfileCommentsByUserProfileUsernameRequest;
import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateReply;
import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateRequest;
import ichop.core.common.domain.EmptyReplyModel;

public interface UserProfileCommentRequester {

    UserProfileCommentCreateReply create(UserProfileCommentCreateRequest request);
    EmptyReplyModel deleteById(String id);
    UserProfileCommentsByUserProfileUsernameReply findByUserProfileUsername(UserProfileCommentsByUserProfileUsernameRequest request);

}
