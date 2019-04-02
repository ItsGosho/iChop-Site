package com.ichop.core.areas.user.events;

import com.ichop.core.areas.log.domain.entities.UserLogType;
import com.ichop.core.areas.log.services.UserLogServices;
import com.ichop.core.areas.role.services.UserRoleServices;
import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
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

        this.userLogServices.create(logMessage,user, UserLogType.ROLE_CHANGE);

    }

}
