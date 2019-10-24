package ichop.core.areas.report.repositories;

import ichop.core.areas.comment.domain.entities.Comment;
import ichop.core.areas.report.domain.entities.CommentReport;
import ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentReportRepository extends ReportRepository<CommentReport> {

    @Query("SELECT case when COUNT(c.id) = 1 then 'true' ELSE 'false' END\n" +
            "from CommentReport AS c\n" +
            "WHERE c.user = :user AND \n" +
            "c.comment = :comment")
    boolean isUserReportedComment(@Param(value = "user") User user, @Param(value = "comment") Comment comment);

}
