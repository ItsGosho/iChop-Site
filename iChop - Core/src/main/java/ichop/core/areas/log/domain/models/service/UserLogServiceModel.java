package ichop.core.areas.log.domain.models.service;

import ichop.core.areas.log.domain.entities.UserLogType;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogServiceModel extends LogServiceModel {

    private UserServiceModel user;
    private UserLogType logType;

}
