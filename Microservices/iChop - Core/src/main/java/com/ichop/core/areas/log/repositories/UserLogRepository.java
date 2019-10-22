package com.ichop.core.areas.log.repositories;

import com.ichop.core.areas.log.domain.entities.UserLog;
import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.user.domain.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends LogRepository<UserLog> {


    List<UserLog> findAllByUserAndLogType(User user, UserLogType userLogType);

}
