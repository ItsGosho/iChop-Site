package com.ichop.webstorage.listeners;

import com.ichop.webstorage.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public void setUserAvatar(Message message) throws IOException, JMSException {
        String username = (String) message.getObjectProperty("username");
        String avatarBinary = (String) message.getObjectProperty("avatar");

        byte[] imageBytes = DatatypeConverter.parseBase64Binary(avatarBinary);

        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

        this.userServices.updateAvatar(username, bufferedImage);

    }

//    @JmsListener(destination = "test")
//    public Message test(Message message) throws IOException {
//        try {
//            System.out.println(message.getObjectProperty("test"));
//            System.out.println(message.getObjectProperty("test2"));
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//        HashMap<String,Object> result = new HashMap<>();
//        result.put("123","asd");
//        result.put("1234",123);
//        return this.convertValuesIntoMessage(result);
//    }

    private Message convertValuesIntoMessage(HashMap<String,Object> values){
        try {

            Session session = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            Message message = session.createMessage();

            for (Map.Entry<String, Object> item : values.entrySet()) {
                message.setObjectProperty(item.getKey(),item.getValue());
            }

            return message;

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }



}
