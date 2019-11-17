package ichop.core.areas.comment.requesters;

import ichop.core.areas.comment.models.CommentType;
import org.ichop.commons.domain.JmsReplyModel;

public interface BaseCommentRequester {
    JmsReplyModel deleteById(String commentId, CommentType type);

    boolean isCreator(String threadId, String creatorUsername, CommentType type);
    JmsReplyModel totalComments(String username);
}
