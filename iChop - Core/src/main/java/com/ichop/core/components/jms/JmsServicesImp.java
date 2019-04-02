package com.ichop.core.components.jms;

import com.ichop.core.base.BaseJMSReceiveModel;
import org.modelmapper.ModelMapper;
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

    private final JmsTemplate jmsTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public JmsServicesImp(JmsTemplate jmsTemplate, ModelMapper modelMapper) {
        this.jmsTemplate = jmsTemplate;
        this.modelMapper = modelMapper;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <ReceiveModel extends BaseJMSReceiveModel> ReceiveModel getResultModel(Map<String, Object> values, Class<ReceiveModel> clazz) {
        return values.get("resultModel") != null ? this.modelMapper.map(values.get("resultModel"), clazz) : null;
    }

    @Override
    public Map<String, Object> messageToHashMap(Message message) {
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

    @Override
    public Map<String, Object> sendAndReceive(String destinationName, HashMap<String, Object> values) {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return convertValuesIntoMessage(values);
            }
        };

        try {
            Message result = this.jmsTemplate.sendAndReceive(destinationName, messageCreator);
            return this.messageToHashMap(result);
        } catch (Exception ex) {
            System.out.println("There was a problem with the connection in destination " + destinationName);
        }

        return new HashMap<>();
    }

    @Override
    public Message convertValuesIntoMessage(HashMap<String, Object> values) {
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
