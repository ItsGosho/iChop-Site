package com.ichop.linkaccount.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.ichop.linkaccount.constants.JmsConstants.*;

@Component
public class JmsServicesImp implements JmsServices {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public JmsServicesImp(JmsTemplate jmsTemplate, ObjectMapper objectMapper, ValidationUtil validationUtil) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Map<String, Object> sendAndReceive(String destinationName, Map<String, Object> values) {
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
    @SuppressWarnings("unchecked")
    public <ReturnModel> ReturnModel getJmsModel(Message message, Class<ReturnModel> clazz) throws JMSException {
        return this.objectMapper
                .convertValue(
                        message.getObjectProperty(RECEIVE_MODEL_PARAMETER_NAME),
                        clazz
                );
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
    public Message convertValuesIntoMessage(Map<String, Object> values) {
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

    @Override
    public Message returnErrors(Object model) {
        Map<String, Object> resultValues = new HashMap<>();
        resultValues.put(SENDING_ERRORS_PARAMETER_NAME, this.validationUtil.getAllErrors(this.validationUtil.validate(model).getAllErrors()));
        return this.convertValuesIntoMessage(resultValues);
    }

    @Override
    public Message returnResultModel(Object model) {
        Map<String, Object> resultValues = new HashMap<>();
        resultValues.put(SENDING_MODEL_PARAMETER_NAME, model != null ? this.objectMapper.convertValue(model, Map.class) : null);
        return this.convertValuesIntoMessage(resultValues);
    }

}
