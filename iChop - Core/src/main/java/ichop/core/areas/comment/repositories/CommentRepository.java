package ichop.core.areas.comment.repositories;

import ichop.core.areas.comment.domain.entities.Comment;
import ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {

    @Query("select count(c.id)\n" +
            " from Comment AS c\n" +
            " where c.creator = :creator")
    int getTotalCommentsOfUser(@Param(value = "creator") User creator);

}
