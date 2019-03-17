package ichop.service.log;

import ichop.domain.entities.log.UserLogType;
import ichop.domain.models.service.log.UserLogServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface UserLogServices {

    UserLogServiceModel createUserLog(String message, UserServiceModel user, UserLogType roleChange);
}
