package ichop.repository.log;

import ichop.domain.entities.log.UserLog;
import ichop.domain.entities.log.UserLogType;
import ichop.domain.entities.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends LogRepository<UserLog> {


    List<UserLog> findAllByUserAndLogType(User user, UserLogType userLogType);

}
