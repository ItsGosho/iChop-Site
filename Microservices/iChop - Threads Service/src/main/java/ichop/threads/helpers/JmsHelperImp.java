package ichop.threads.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.domain.models.jms.BaseReceiveModel;
import ichop.threads.domain.models.jms.BaseSendModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import static ichop.threads.constants.JmsLoggingConstants.*;

@Component
@SuppressWarnings("all")
public class JmsHelperImp<S extends BaseSendModel, R extends BaseReceiveModel> implements JmsHelper {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public JmsHelperImp(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public <S,R> R sendAndReceive(String destination, S model, Class<R> clazz) {
        LOG.info(String.format(SEND_AND_RECEIVED_STARTED, destination));

        MessageCreator message = this.createMessage(model);
        Message result = this.jmsTemplate.sendAndReceive(destination, message);

        return this.getResultModel(result, clazz);
    }

    @Override
    public <S> void send(String destination, S model) {
        LOG.info(String.format(SEND_STARTED, destination));

        MessageCreator message = this.createMessage(model);
        this.jmsTemplate.send(destination, message);
    }

    @Override
    public <S> void replyTo(String destination, String correlationId, S model) {
        LOG.info(String.format(REPLY_TO_STARTED, destination));

        MessageCreator message = this.createMessage(model, correlationId);
        this.jmsTemplate.send(destination, message);
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

    private <S> MessageCreator createMessage(S model) {
        return this.createMessage(model, this.randomId());
    }

    private <S> MessageCreator createMessage(S model, String correlationId) {
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