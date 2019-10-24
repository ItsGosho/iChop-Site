package ichop.core.areas.log.repositories;

import ichop.core.areas.log.domain.entities.BaseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LogRepository<T extends BaseLog> extends JpaRepository<T,String> {
}
