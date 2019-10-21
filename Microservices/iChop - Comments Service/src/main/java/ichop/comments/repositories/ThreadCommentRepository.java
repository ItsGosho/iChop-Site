package ichop.comments.repositories;

import ichop.comments.domain.entities.ThreadComment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadCommentRepository extends CommentRepository<ThreadComment> {

    boolean existsByThreadId(String threadId);
    List<ThreadComment> findByThreadId(String threadId);

}
