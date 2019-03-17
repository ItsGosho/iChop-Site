package ichop.domain.models.service.log;

import ichop.domain.entities.log.UserLogType;
import ichop.domain.models.service.user.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogServiceModel extends LogServiceModel {

    private UserServiceModel user;
    private UserLogType logType;

}
