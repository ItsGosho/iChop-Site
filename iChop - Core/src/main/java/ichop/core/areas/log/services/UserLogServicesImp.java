package ichop.core.areas.log.services;

import ichop.core.areas.log.domain.entities.UserLog;
import ichop.core.areas.log.domain.entities.UserLogType;
import ichop.core.areas.log.domain.models.binding.UserLogCreateBindingModel;
import ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import ichop.core.areas.log.repositories.UserLogRepository;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import ichop.core.areas.user.exceptions.UserNotFoundException;
import ichop.core.base.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLogServicesImp extends BaseService<UserLog, UserLogRepository> implements UserLogServices {

    @Autowired
    public UserLogServicesImp(ModelMapper modelMapper, UserLogRepository repository) {
        super(modelMapper, repository);
    }

    @Override
    public UserLogServiceModel create(UserLogCreateBindingModel userLogCreateBindingModel) {

        if (userLogCreateBindingModel.getUser() == null) {
            throw new UserNotFoundException();
        }

        UserLogServiceModel userLog = this.modelMapper.map(userLogCreateBindingModel, UserLogServiceModel.class);
        userLog.setHappenedOn(LocalDateTime.now());

        return this.save(userLog, UserLogServiceModel.class);
    }

    @Override
    public List<UserLogServiceModel> findAllByUserAndLogType(UserServiceModel user, UserLogType logType) {

        if (user == null) {
            throw new UserNotFoundException();
        }

        User entityUser = this.modelMapper.map(user, User.class);
        List<UserLog> foundedLogs = this.repository.findAllByUserAndLogType(entityUser, logType);

        return foundedLogs.stream().map(x -> this.modelMapper.map(x, UserLogServiceModel.class)).collect(Collectors.toList());
    }

}
