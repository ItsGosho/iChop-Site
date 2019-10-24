package ichop.core.areas.report.repositories;

import ichop.core.areas.report.domain.entities.ThreadReport;
import ichop.core.areas.thread.domain.entities.Thread;
import ichop.core.areas.user.domain.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThreadReportRepository extends ReportRepository<ThreadReport> {


    @Query("SELECT case when COUNT(t.id) = 1 then 'true' ELSE 'false' END\n" +
            "from ThreadReport AS t\n" +
            "WHERE t.user = :user AND \n" +
            "t.thread = :thread")
    boolean isUserReportedThread(@Param(value = "user") User user, @Param(value = "thread") Thread thread);
}
