package com.ichop.core.repository.log;

import com.ichop.core.domain.entities.log.UserLog;
import com.ichop.core.domain.entities.log.UserLogType;
import com.ichop.core.domain.entities.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends LogRepository<UserLog> {


    List<UserLog> findAllByUserAndLogType(User user, UserLogType userLogType);

}
