package ichop.threads.common.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.common.domain.BaseReplyModel;
import ichop.threads.common.domain.BaseRequestModel;
import ichop.threads.common.domain.ErrorReplyModel;
import ichop.threads.common.validation.ValidationHelper;
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

import static ichop.threads.common.constants.JmsLoggingConstants.*;

@Component
@SuppressWarnings("all")
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
    public <S extends BaseReplyModel, R extends BaseRequestModel> S sendAndReceive(String destination, R model, Class<S> clazz) {
        LOG.info(String.format(SEND_AND_RECEIVED_STARTED, destination));

        MessageCreator message = this.createMessage(model);
        Message result = this.jmsTemplate.sendAndReceive(destination, message);

        return this.getResultModel(result, clazz);
    }

    @Override
    public <S extends BaseRequestModel> void send(String destination, S model) {
        LOG.info(String.format(SEND_STARTED, destination));

        MessageCreator message = this.createMessage(model);
        this.jmsTemplate.send(destination, message);
    }

    @Override
    public <S extends BaseReplyModel> void replySuccessful(Message message, S model,String msg) {
        try {
            model.setSuccessful(true);
            model.setMessage(msg);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), model);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <R> R getResultModel(Message message, Class<R> clazz) {

        try {
            String body = message.getBody(String.class);
            return this.objectMapper.readValue(body, clazz);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <R extends BaseRequestModel> void replyValidationError(Message message, R receiveModel) {

        try {
            LOG.info(String.format(VALIDATION_ERROR_REPLY_WILL_START, message.getJMSReplyTo()));
            String error = this.validationHelper.getValidationError(receiveModel);
            ErrorReplyModel errorSendModel = new ErrorReplyModel(error);

            this.replyTo(message.getJMSReplyTo(), message.getJMSCorrelationID(), errorSendModel);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private <S extends BaseReplyModel> void replyTo(Destination destination, String correlationId, S model) {
        LOG.info(String.format(REPLY_TO_STARTED, destination));

        MessageCreator message = this.createMessage(model, correlationId);
        this.jmsTemplate.send(destination, message);
    }

    private MessageCreator createMessage (Object model) {
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
