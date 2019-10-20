package ichop.comments.repositories;

import ichop.comments.domain.entities.ThreadComment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadCommentRepository extends CommentRepository<ThreadComment> {


}
