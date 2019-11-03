package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.jms.all.UserProfileCommentsByUserProfileUsernameRequest;
import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface UserProfileCommentRequester {

    JmsReplyModel create(UserProfileCommentCreateRequest request);
    JmsReplyModel deleteById(String id);
    JmsReplyModel findByUserProfileUsername(UserProfileCommentsByUserProfileUsernameRequest request);

}
