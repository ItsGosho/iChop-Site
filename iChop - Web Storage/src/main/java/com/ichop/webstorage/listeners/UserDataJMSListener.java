package com.ichop.webstorage.listeners;

import com.ichop.webstorage.components.JmsServices;
import com.ichop.webstorage.components.ValidationUtil;
import com.ichop.webstorage.domain.models.jms.UserUpdateAvatarJmsReceiveModel;
import com.ichop.webstorage.listeners.anotations.SetUserAvatarListener;
import com.ichop.webstorage.services.UserDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class UserDataJMSListener {

    private final UserDataServices userDataServices;
    private final JmsServices jmsServices;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserDataJMSListener(UserDataServices userDataServices, JmsServices jmsServices, ValidationUtil validationUtil) {
        this.userDataServices = userDataServices;
        this.jmsServices = jmsServices;
        this.validationUtil = validationUtil;
    }


    @SetUserAvatarListener
    public void setUserAvatar(Message message) throws IOException, JMSException {
        UserUpdateAvatarJmsReceiveModel receivedModel = this.jmsServices.getJmsModel(message, UserUpdateAvatarJmsReceiveModel.class);

        if (this.validationUtil.validate(receivedModel).hasErrors()) {
            return;
        }

        this.userDataServices.updateAvatar(receivedModel.getUsername(), receivedModel.getAvatar());
    }


}
