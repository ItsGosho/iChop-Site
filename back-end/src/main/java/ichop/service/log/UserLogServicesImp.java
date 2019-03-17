package ichop.service.log;

import ichop.domain.entities.log.UserLog;
import ichop.domain.entities.log.UserLogType;
import ichop.domain.models.service.log.UserLogServiceModel;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.repository.log.UserLogRepository;
import ichop.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserLogServicesImp extends BaseService<UserLog, UserLogRepository> implements UserLogServices {

    @Autowired
    public UserLogServicesImp(ModelMapper modelMapper, UserLogRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public UserLogServiceModel createUserLog(String message, UserServiceModel user, UserLogType roleChange) {

        UserLogServiceModel userLog = new UserLogServiceModel();
        userLog.setHappenedOn(LocalDateTime.now());
        userLog.setUser(user);
        userLog.setMessage(message);
        userLog.setLogType(roleChange);

        return super.save(userLog,UserLogServiceModel.class);
    }

}
