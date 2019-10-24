package ichop.core.areas.user.events;

import ichop.core.areas.log.domain.entities.UserLogType;
import ichop.core.areas.log.domain.models.binding.UserLogCreateBindingModel;
import ichop.core.areas.log.services.UserLogServices;
import ichop.core.areas.role.services.UserRoleServices;
import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserRoleChangeEventListener {

    private final UserLogServices userLogServices;
    private final UserRoleServices userRoleServices;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleChangeEventListener(UserLogServices userLogServices, UserRoleServices userRoleServices, ModelMapper ModelMapper) {
        this.userLogServices = userLogServices;
        this.userRoleServices = userRoleServices;
        this.modelMapper = ModelMapper;
    }

    @Async
    @EventListener
    public void roleChangeEvent(UserRoleChangeEvent userRoleChangeEvent){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String creationMaker = "anonymousUser";

        if(principal instanceof User){
            creationMaker = this.modelMapper.map(principal,UserServiceModel.class).getUsername();
        }

        UserServiceModel user = this.modelMapper.map(userRoleChangeEvent.getUser(), UserServiceModel.class);

        String logMessage = String.format("%s has changed the role of %s to %s",creationMaker,user.getUsername(),this.userRoleServices.findHighestOfUser(user).getAuthority());

        this.userLogServices.create(this.createLog(logMessage,user, UserLogType.ROLE_CHANGE));

    }

    private UserLogCreateBindingModel createLog(String logMessage, UserServiceModel user, UserLogType logType){
        UserLogCreateBindingModel userLogCreateBindingModel = new UserLogCreateBindingModel();
        userLogCreateBindingModel.setUser(user);
        userLogCreateBindingModel.setMessage(logMessage);
        userLogCreateBindingModel.setLogType(logType);

        return userLogCreateBindingModel;
    }

}
