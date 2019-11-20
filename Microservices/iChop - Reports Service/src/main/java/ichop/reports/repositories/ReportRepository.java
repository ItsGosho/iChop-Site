package ichop.reports.repositories;

import ichop.reports.domain.entities.Report;
import ichop.reports.domain.enums.Type;
import org.ichop.commons.domain.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report,String> {

    boolean existsByTypeAndId(Type type, String id);
    boolean existsByTypeAndUserIdAndEntityId(Type type, String userId,String entityId);
    boolean deleteByTypeAndId(Type type, String id);

}
