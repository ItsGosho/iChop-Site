package org.ichop.commons.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ichop.commons.domain.ReplyCandidate;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validation.ValidationHelper;
import org.ichop.commons.domain.JmsReplyModel;
import org.jgroups.blocks.ReplCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import static org.ichop.commons.constants.JmsLoggingConstants.*;

@Component
public class JmsHelperImp implements JmsHelper {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    private final JmsTemplate jmsTemplate;
    private final ValidationHelper validationHelper;
    private final ObjectMapper objectMapper;

    @Autowired
    public JmsHelperImp(JmsTemplate jmsTemplate, ValidationHelper validationHelper, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.validationHelper = validationHelper;
        this.objectMapper = objectMapper;
    }

    @Override
    public <R extends RequestCandidate> JmsReplyModel sendAndReceive(String destination, R request) {
        LOG.info(String.format(SEND_AND_RECEIVED_STARTED, destination));

        MessageCreator message = this.createMessage(request);
        Message result = this.jmsTemplate.sendAndReceive(destination, message);

        return this.extractReply(result);
    }

    @Override
    public <R extends RequestCandidate> void send(String destination, R request) {
        LOG.info(String.format(SEND_STARTED, destination));

        MessageCreator message = this.createMessage(request);
        this.jmsTemplate.send(destination, message);
    }

    @Override
    public <R extends ReplyCandidate> void replySuccessful(Message message, R reply, String msg) {
        try {
            JmsReplyModel jmsReplyModel = new JmsReplyModel(true, msg, reply);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), jmsReplyModel);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JmsReplyModel extractReply(Message message) {

        try {
            String body = message.getBody(String.class);

            return this.objectMapper.readValue(body, JmsReplyModel.class);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <R> R toModel(Message message, Class<R> clazz) {

        try {
            String body = message.getBody(String.class);

            return this.objectMapper.readValue(body, clazz);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <R extends ReplyCandidate> R extractReplyData(Message message, Class<R> clazz) {
        JmsReplyModel replyModel = this.extractReply(message);

        return this.objectMapper.convertValue(replyModel.getData(), clazz);
    }

    @Override
    public void replyValidationError(Message message, Object data) {

        try {
            LOG.info(String.format(VALIDATION_ERROR_REPLY_WILL_START, message.getJMSReplyTo()));
            String error = this.validationHelper.getValidationError(data);
            JmsReplyModel jmsReplyModel = new JmsReplyModel(false, error);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), jmsReplyModel);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <R extends ReplyCandidate> void replyError(Message message, R reply, String msg) {

        try {
            LOG.info(String.format(ERROR_REPLY_WILL_START, message.getJMSReplyTo()));
            JmsReplyModel jmsReplyModel = new JmsReplyModel(true, msg, reply);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), jmsReplyModel);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void replyTo(Destination destination, String correlationId, Object data) {
        LOG.info(String.format(REPLY_TO_STARTED, destination));

        MessageCreator message = this.createMessage(data, correlationId);
        this.jmsTemplate.send(destination, message);
    }

    private MessageCreator createMessage(Object model) {
        return this.createMessage(model, this.randomId());
    }

    private MessageCreator createMessage(Object model, String correlationId) {
        return session -> {
            try {
                Message message = session.createTextMessage(this.objectMapper.writeValueAsString(model));
                message.setJMSCorrelationID(correlationId);
                return message;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    private String randomId() {
        return UUID.randomUUID().toString();
    }
}
