package ichop.comments.services;

import ichop.comments.domain.entities.ThreadComment;
import ichop.comments.domain.models.service.ThreadCommentServiceModel;

public interface ThreadCommentServices extends CommentServices<ThreadCommentServiceModel> {

    boolean existsByThreadId(String threadId);
}
