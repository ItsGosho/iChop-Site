package com.ichop.webstorage.listeners;

import com.ichop.webstorage.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JMSListeners {

    private final UserServices userServices;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JMSListeners(UserServices userServices, JmsTemplate jmsTemplate) {
        this.userServices = userServices;
        this.jmsTemplate = jmsTemplate;
    }


    @JmsListener(destination = "set_user_avatar")
    public void setUserAvatar(ObjectMessage objectMessage) throws IOException {
        HashMap<String, Object> messages = this.objectMessageToMessages(objectMessage);

        String username = (String) messages.get("username");
        String avatarBinary = (String) messages.get("avatar");

        byte[] imageBytes = DatatypeConverter.parseBase64Binary(avatarBinary);

        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

        this.userServices.updateAvatar(username, bufferedImage);

    }

    @SuppressWarnings("unchecked")
    private HashMap<String, Object> objectMessageToMessages(ObjectMessage objectMessage) {
        Object object = null;
        try {
            object = objectMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return (HashMap<String, Object>) object;
    }


}
