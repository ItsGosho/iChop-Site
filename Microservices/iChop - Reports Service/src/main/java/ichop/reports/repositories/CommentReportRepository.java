package ichop.reports.repositories;

import ichop.reports.domain.entities.CommentReport;
import ichop.reports.domain.entities.ThreadReport;
import ichop.reports.domain.enums.Type;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReportRepository extends ReportRepository<CommentReport> {

    boolean existsByUserIdAndCommentIdAndType(String userId, String commentId, Type type);

}
