package com.ichop.core.components.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

@Service
public class JmsServicesImp implements JmsServices {

    public static final String UPDATE_AVATAR_DESTINATION = "set_user_avatar";
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsServicesImp(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public void sendUpdateAvatarRequest(String username, String imageAsBase64String) {
        HashMap<String,Object> values = new HashMap<>();
        values.put("username",username);
        values.put("avatar",imageAsBase64String);

        try {
            this.jmsTemplate.convertAndSend(UPDATE_AVATAR_DESTINATION, this.convertValuesIntoMessage(values));
        }catch (Exception ex){
            System.out.println("Cannot send the request via the jms ,maybe the server is down?");
        }

    }

//    HashMap<String,Object> values = new HashMap<>();
//        values.put("test",123);
//        values.put("test2","asd23");
//    Message a = this.sendAndReceive("test",values);
//        System.out.println(a.getObjectProperty("1234"));
//        System.out.println(a.getObjectProperty("123"));
//        System.out.println(a.getObjectProperty("timestamp"));

    private Message sendAndReceive(String destinationName, HashMap<String,Object> values){
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return convertValuesIntoMessage(values);
            }
        };
        return this.jmsTemplate.sendAndReceive(destinationName,messageCreator);
    }


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
