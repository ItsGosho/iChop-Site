package ichop.core.areas.report.repositories;

import ichop.core.areas.post.domain.entities.Post;
import ichop.core.areas.report.domain.entities.PostReport;
import ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostReportRepository extends ReportRepository<PostReport> {

    @Query("SELECT case when COUNT(p.id) = 1 then 'true' ELSE 'false' END\n" +
            "from PostReport AS p\n" +
            "WHERE p.user = :user AND \n" +
            "p.post = :post")
    boolean isUserReportedPost(@Param(value = "user") User user, @Param(value = "post") Post post);

}
