package ichop.reports.repositories;

import ichop.reports.common.domain.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReportRepository<E extends BaseEntity> extends MongoRepository<E,String> {



}
