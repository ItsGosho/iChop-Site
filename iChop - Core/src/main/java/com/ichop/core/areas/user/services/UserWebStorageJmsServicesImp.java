package com.ichop.core.areas.user.services;

import com.ichop.core.areas.user.domain.models.jms.UserUpdateAvatarJmsSendModel;
import com.ichop.core.components.jms.JmsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ichop.core.areas.user.constants.UserWebStorageJmsConstants.UPDATE_AVATAR_DESTINATION;

@Service
public class UserWebStorageJmsServicesImp implements UserWebStorageJmsServices {

    private final JmsServices jmsServices;

    @Autowired
    public UserWebStorageJmsServicesImp(JmsServices jmsServices) {
        this.jmsServices = jmsServices;
    }

    @Override
    public void sendUpdateAvatarRequest(String username, String imageAsBase64String) {
        UserUpdateAvatarJmsSendModel bindingModel = new UserUpdateAvatarJmsSendModel();
        bindingModel.setUsername(username);
        bindingModel.setAvatar(imageAsBase64String);

        this.jmsServices.sendModel(bindingModel, UPDATE_AVATAR_DESTINATION);

    }

}
