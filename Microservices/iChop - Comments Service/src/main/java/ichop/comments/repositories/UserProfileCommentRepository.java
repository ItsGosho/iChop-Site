package ichop.comments.repositories;

import ichop.comments.domain.entities.UserProfileComment;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileCommentRepository extends CommentRepository<UserProfileComment> {
}
