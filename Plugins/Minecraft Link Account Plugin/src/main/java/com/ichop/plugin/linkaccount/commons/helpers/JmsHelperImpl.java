package com.ichop.plugin.linkaccount.commons.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.plugin.linkaccount.commons.domain.JmsReplyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.jms.*;
import java.io.IOException;
import java.util.UUID;

public class JmsHelperImpl implements JmsHelper {

    private Logger LOG = LogManager.getLogger(JmsHelperImpl.class);

    private final Session session;
    private final ObjectMapper objectMapper;

    @Inject
    public JmsHelperImpl(Session session, ObjectMapper objectMapper) {
        this.session = session;
        this.objectMapper = objectMapper;
    }

    @Override
    public void replySuccessful(Message message, Object reply, String msg) {
        try {
            JmsReplyModel jmsReplyModel = new JmsReplyModel(true, msg, reply);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), jmsReplyModel);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void replyValidationError(Message message, String error) {

        try {
            JmsReplyModel jmsReplyModel = new JmsReplyModel(false, error);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), jmsReplyModel);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public JmsReplyModel extractReply(Message message) {

        try {
            String body = message.getBody(String.class);

            return this.objectMapper.readValue(body, JmsReplyModel.class);
        } catch (JMSException | IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public <R> R toModel(Message message, Class<R> clazz) {

        try {
            String body = message.getBody(String.class);

            return this.objectMapper.readValue(body, clazz);
        } catch (JMSException | IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public <R> R extractReplyData(Message message, Class<R> clazz) {
        JmsReplyModel replyModel = this.extractReply(message);

        return this.objectMapper.convertValue(replyModel.getData(), clazz);
    }

    @Override
    public <R> void replyError(Message message, R reply, String msg) {

        try {
            JmsReplyModel jmsReplyModel = new JmsReplyModel(true, msg, reply);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), jmsReplyModel);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    private void replyTo(Destination destination, String correlationId, Object data) {
        try {
            MessageProducer messageProducer = this.session.createProducer(destination);
            Message message = this.createMessage(data, correlationId);
            messageProducer.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Message createMessage(Object model, String correlationId) {
        try {
            Message message = this.session.createTextMessage(this.objectMapper.writeValueAsString(model));
            message.setJMSCorrelationID(correlationId);
            return message;
        } catch (JMSException | com.fasterxml.jackson.core.JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
