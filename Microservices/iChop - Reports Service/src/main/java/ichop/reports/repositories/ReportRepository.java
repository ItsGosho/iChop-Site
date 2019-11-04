package ichop.reports.repositories;

import org.ichop.commons.domain.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReportRepository<E extends MongoEntity> extends MongoRepository<E,String> {



}
