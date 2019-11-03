package ichop.core.areas.comment.requester;

import ichop.core.areas.comment.models.CommentType;
import org.ichop.commons.domain.JmsReplyModel;

public interface BaseCommentRequester {
    JmsReplyModel deleteById(String commentId, CommentType type);

    JmsReplyModel isCreator(String threadId, String creatorUsername, CommentType type);
}
