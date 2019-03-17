package ichop.events;

import ichop.domain.entities.log.UserLogType;
import ichop.domain.entities.users.User;
import ichop.domain.models.service.user.UserServiceModel;
import ichop.service.log.UserLogServices;
import ichop.service.role.UserRoleServices;
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
    public UserRoleChangeEventListener(UserLogServices userLogServices, UserRoleServices userRoleServices, ModelMapper modelMapper) {
        this.userLogServices = userLogServices;
        this.userRoleServices = userRoleServices;
        this.modelMapper = modelMapper;
    }

    @Async
    @EventListener
    public void roleChangeEvent(UserRoleChangeEvent userRoleChangeEvent){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String creationMaker = "anonymousUser";

        if(principal instanceof User){
            creationMaker = ((UserServiceModel) principal).getUsername();
        }


        UserServiceModel user = this.modelMapper.map(userRoleChangeEvent.getUser(), UserServiceModel.class);

        String logMessage = String.format("%s has changed the role of %s to %s",creationMaker,user.getUsername(),this.userRoleServices.findHighestRoleOfUser(user).getAuthority());

        this.userLogServices.createUserLog(logMessage,user, UserLogType.ROLE_CHANGE);

    }

}
