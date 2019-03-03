package ichop.repository.threads.report;

import ichop.domain.entities.threads.reaction.BaseReaction;
import ichop.domain.entities.threads.report.BaseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReportRepository<T extends BaseReport> extends JpaRepository<T,String> {

}
