package ichop.core.areas.reaction.repositories;

import ichop.core.areas.reaction.domain.entities.ThreadReaction;
import ichop.core.areas.thread.domain.entities.Thread;
import ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadReactionRepository extends ReactionRepository<ThreadReaction> {

    @Query("SELECT case when COUNT(r.id) = 1 then 'true' ELSE 'false' END\n" +
            "from ThreadReaction AS r\n" +
            "WHERE r.user = :user AND \n" +
            "r.thread = :thread")
    boolean isUserReactedAtThatThread(@Param(value = "user") User user, @Param(value = "thread") Thread thread);
}
