package ichop.comments.repositories;

import ichop.comments.domain.entities.ThreadComment;
import ichop.comments.domain.entities.UserProfileComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileCommentRepository extends CommentRepository<UserProfileComment> {

    List<UserProfileComment> findByUserProfileId(String userProfileId);

}
