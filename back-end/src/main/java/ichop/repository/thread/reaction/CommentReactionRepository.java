package ichop.repository.thread.reaction;

import ichop.domain.entities.threads.Comment;
import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.reaction.CommentReaction;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.entities.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends ReactionRepository<CommentReaction> {

    @Query("SELECT case when COUNT(r.id) = 1 then 'true' ELSE 'false' END\n" +
            "from CommentReaction AS r\n" +
            "WHERE r.user = :user AND \n" +
            "r.comment = :comment")
    boolean isUserLikedThatComment(@Param(value = "user") User user, @Param(value = "comment") Comment comment);

}
