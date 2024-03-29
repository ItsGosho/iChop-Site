package ichop.core.areas.comment.requesters;

import ichop.core.areas.comment.models.jms.create.UserProfileCommentCreateRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface UserProfileCommentRequester {

    JmsReplyModel create(UserProfileCommentCreateRequest request);
    JmsReplyModel findByUserProfileUsername(String userProfileUsername);

}
