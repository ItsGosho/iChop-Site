package com.ichop.core.areas.log.services;

import com.ichop.core.areas.log.domain.entities.UserLog;
import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.domain.models.service.UserLogServiceModel;
import com.ichop.core.areas.log.repositories.UserLogRepository;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import com.ichop.core.areas.user.exceptions.UserNotFoundException;
import com.ichop.core.base.BaseService;
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
    public UserLogServiceModel create(String message, UserServiceModel user, UserLogType roleChange) {

        if(user == null){
            throw new UserNotFoundException();
        }

        UserLogServiceModel userLog = new UserLogServiceModel();
        userLog.setHappenedOn(LocalDateTime.now());
        userLog.setUser(user);
        userLog.setMessage(message);
        userLog.setLogType(roleChange);

        return super.save(userLog,UserLogServiceModel.class);
    }

    @Override
    public List<UserLogServiceModel> findAllByUserAndLogType(UserServiceModel user, UserLogType logType) {
        User entityUser = super.modelMapper.map(user,User.class);
        List<UserLog> foundedLogs = super.repository.findAllByUserAndLogType(entityUser,logType);

        return foundedLogs.stream().map(x-> super.modelMapper.map(x,UserLogServiceModel.class)).collect(Collectors.toList());
    }

}
