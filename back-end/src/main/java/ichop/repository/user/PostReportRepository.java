package ichop.repository.user;

import ichop.domain.entities.users.post.PostReport;
import ichop.repository.base.ReportRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends ReportRepository<PostReport> {
}
