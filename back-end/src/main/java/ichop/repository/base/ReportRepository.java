package ichop.repository.base;

import ichop.domain.entities.base.BaseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReportRepository<T extends BaseReport> extends JpaRepository<T,String> {

}