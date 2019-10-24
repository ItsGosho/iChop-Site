package ichop.core.areas.log.repositories;

import ichop.core.areas.log.domain.entities.UserLog;
import ichop.core.areas.log.domain.entities.UserLogType;
import ichop.core.areas.user.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends LogRepository<UserLog> {


    List<UserLog> findAllByUserAndLogType(User user, UserLogType userLogType);

}
