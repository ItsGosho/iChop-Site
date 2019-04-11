package com.ichop.core.components.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.core.base.BaseJMSReceiveModel;
import com.ichop.core.base.BaseJMSSendModel;
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

import static com.ichop.core.constants.JMSConstants.RECEIVE_MODEL_PARAMETER_NAME;
import static com.ichop.core.constants.JMSConstants.SEND_MODEL_PARAMETER_NAME;

@Service
public class JmsServicesImp implements JmsServices {

    private final JmsTemplate jmsTemplate;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private Session session;

    @Autowired
    public JmsServicesImp(JmsTemplate jmsTemplate, ModelMapper modelMapper, ObjectMapper objectMapper) throws JMSException {
        this.jmsTemplate = jmsTemplate;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.session = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("I WAS HERE!");
    }


    @Override
    @SuppressWarnings("unchecked")
    public <ReceiveModel extends BaseJMSReceiveModel> ReceiveModel getResultModel(Map<String, Object> values, Class<ReceiveModel> clazz) {
        return values.get(RECEIVE_MODEL_PARAMETER_NAME) != null ? this.objectMapper.convertValue(values.get(RECEIVE_MODEL_PARAMETER_NAME), clazz) : null;
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
    public <ReceiveModel extends BaseJMSReceiveModel, SendModel extends BaseJMSSendModel> ReceiveModel sendAndReceiveModel(SendModel sendModel, Class<ReceiveModel> receiveModel, String destination) {

        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put(SEND_MODEL_PARAMETER_NAME, this.objectMapper.convertValue(sendModel, Map.class));
        MessageCreator message = this.convertMessageIntoMessageCreator(this.convertValuesIntoMessage(valuesToSend));

        try {
            Message resultMessage = this.jmsTemplate.sendAndReceive(destination, message);
            Map<String, Object> resultValues = this.messageToHashMap(resultMessage);
            ReceiveModel resultModel = this.getResultModel(resultValues, receiveModel);
            return resultModel;
        } catch (Exception ex) {
            System.out.println("There was a problem with the connection in destination " + destination + " " + ex.getMessage());
        }

        try {
            return receiveModel.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <SendModel extends BaseJMSSendModel> void sendModel(SendModel sendModel, String destination) {

        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put(SEND_MODEL_PARAMETER_NAME, this.objectMapper.convertValue(sendModel, Map.class));
        MessageCreator message = this.convertMessageIntoMessageCreator(this.convertValuesIntoMessage(valuesToSend));

        try {
            this.jmsTemplate.send(destination, message);
        } catch (Exception ex) {
            System.out.println("There was a problem with the connection in destination " + destination + " " + ex.getMessage());
        }
    }

    @Override
    public Map<String, Object> sendAndReceive(String destinationName, HashMap<String, Object> values) {
        MessageCreator messageCreator = session -> convertValuesIntoMessage(values);

        try {
            Message result = this.jmsTemplate.sendAndReceive(destinationName, messageCreator);
            return this.messageToHashMap(result);
        } catch (Exception ex) {
            System.out.println("There was a problem with the connection in destination " + destinationName);
        }

        return new HashMap<>();
    }

    @Override
    public MessageCreator convertMessageIntoMessageCreator(Message message) {
        return session -> message;
    }

    @Override
    public Message convertValuesIntoMessage(HashMap<String, Object> values) {
        try {
            Message message = this.session.createMessage();

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
