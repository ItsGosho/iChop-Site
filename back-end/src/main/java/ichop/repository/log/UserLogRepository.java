package ichop.repository.log;

import ichop.domain.entities.log.UserLog;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends LogRepository<UserLog> {

}
