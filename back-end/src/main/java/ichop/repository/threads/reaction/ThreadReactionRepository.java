package ichop.repository.threads.reaction;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.threads.reaction.ThreadReaction;
import ichop.domain.entities.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadReactionRepository extends ReactionRepository<ThreadReaction> {

    @Query("SELECT case when COUNT(r.id) = 1 then 'true' ELSE 'false' END\n" +
            "from ThreadReaction AS r\n" +
            "WHERE r.user = :user AND \n" +
            "r.thread = :thread")
    boolean isUserLikedThatThread(@Param(value = "user") User user, @Param(value = "thread") Thread thread);

}
