package ichop.repository.threads.reaction;

import ichop.domain.entities.threads.reaction.BaseReaction;
import ichop.domain.entities.threads.reaction.ReactionType;
import ichop.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface ReactionRepository<T extends BaseReaction> extends JpaRepository<T,String> {

    @Query("SELECT COUNT(r.id)\n" +
            "FROM #{#entityName} AS r\n" +
            "WHERE r.user = :user")
    int getUserTotalReactions(@Param(value = "user") User user);

    @Query("SELECT COUNT(r.id)\n" +
            "FROM #{#entityName} AS r\n" +
            "WHERE r.user = :user AND  r.reactionType = :reactionType")
    int getUserTotalReactions(@Param(value = "user") User user, @Param(value = "reactionType") ReactionType reactionType);

}
