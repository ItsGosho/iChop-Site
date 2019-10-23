package ichop.reports.repositories;

import ichop.reports.domain.entities.ThreadReport;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadReportRepository extends ReportRepository<ThreadReport> {

    boolean existsByUserIdAndThreadId(String userId,String threadId);
    boolean deleteByThreadId(String threadId);

}
