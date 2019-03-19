package ichop.repository.log;

import ichop.domain.entities.log.BaseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LogRepository<T extends BaseLog> extends JpaRepository<T,String> {
}
