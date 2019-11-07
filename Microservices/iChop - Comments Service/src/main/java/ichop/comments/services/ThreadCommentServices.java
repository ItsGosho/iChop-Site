package ichop.comments.services;

import ichop.comments.domain.models.service.ThreadCommentServiceModel;

import java.util.List;

public interface ThreadCommentServices extends CommentServices<ThreadCommentServiceModel> {

    boolean existsByThreadId(String threadId);

    List<ThreadCommentServiceModel> findAllByThreadId(String threadId);

    <M> List<M> findAllByThreadId(String threadId, Class<M> clazz);
}
