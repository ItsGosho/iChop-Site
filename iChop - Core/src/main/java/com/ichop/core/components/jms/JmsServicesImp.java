package com.ichop.core.components.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class JmsServicesImp implements JmsServices {

    public static final String UPDATE_AVATAR_DESTINATION = "ichop_web_storage-set_user_avatar";
    public static final String IS_PLAYER_LINK_KEY_VALID = "ichop_minecraft-is_player_link_key_valid";
    public static final String GET_PLAYER_UUID_BY_LINK_KEY = "ichop_minecraft-get_player_uuid_by_key";

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsServicesImp(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public void sendUpdateAvatarRequest(String username, String imageAsBase64String) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("username", username);
        values.put("avatar", imageAsBase64String);

        try {
            this.jmsTemplate.convertAndSend(UPDATE_AVATAR_DESTINATION, this.convertValuesIntoMessage(values));
        } catch (Exception ex) {
            System.out.println("Cannot send the request via the jms ,maybe the server is down?");
        }

    }

    @Override
    public boolean isPlayerLinkKeyValid(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("key", key);

        Message message = this.sendAndReceive(IS_PLAYER_LINK_KEY_VALID, valuesToSend);
        Map<String,Object> result = this.messageToHashMap(message);

        boolean isValid = (boolean) result.get("isValid");

        return isValid;
    }

    @Override
    public Map<String, Object> getPlayerUUIDByLinkKey(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("key", key);

        Message message = this.sendAndReceive(GET_PLAYER_UUID_BY_LINK_KEY, valuesToSend);
        Map<String,Object> result = this.messageToHashMap(message);

        return result;
    }

    @Override
    public boolean sendSiteUserToPlayerLinkConnection(String key, String userUsername) {
        return false;
    }

//    HashMap<String,Object> values = new HashMap<>();
//        values.put("test",123);
//        values.put("test2","asd23");
//    Message a = this.sendAndReceive("test",values);
//        System.out.println(a.getObjectProperty("1234"));
//        System.out.println(a.getObjectProperty("123"));
//        System.out.println(a.getObjectProperty("timestamp"));

    private Map<String, Object> messageToHashMap(Message message) {
        Map<String, Object> result = new HashMap<>();
        try {
            Enumeration keys = message.getPropertyNames();
            Iterator iterator = keys.asIterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                result.put(key, message.getObjectProperty(key));
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Message sendAndReceive(String destinationName, HashMap<String, Object> values) {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return convertValuesIntoMessage(values);
            }
        };
        return this.jmsTemplate.sendAndReceive(destinationName, messageCreator);
    }


    private Message convertValuesIntoMessage(HashMap<String, Object> values) {
        try {

            Session session = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            Message message = session.createMessage();

            for (Map.Entry<String, Object> item : values.entrySet()) {
                message.setObjectProperty(item.getKey(), item.getValue());
            }

            return message;

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

}
