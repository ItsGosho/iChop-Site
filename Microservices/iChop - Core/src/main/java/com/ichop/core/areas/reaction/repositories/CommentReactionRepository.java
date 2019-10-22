package com.ichop.core.areas.reaction.repositories;

import com.ichop.core.areas.comment.domain.entities.Comment;
import com.ichop.core.areas.reaction.domain.entities.CommentReaction;
import com.ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReactionRepository extends ReactionRepository<CommentReaction> {

    @Query("SELECT case when COUNT(r.id) = 1 then 'true' ELSE 'false' END\n" +
            "from CommentReaction AS r\n" +
            "WHERE r.user = :user AND \n" +
            "r.comment = :comment")
    boolean isUserReactedAtThatComment(@Param(value = "user") User user, @Param(value = "comment") Comment comment);

}
