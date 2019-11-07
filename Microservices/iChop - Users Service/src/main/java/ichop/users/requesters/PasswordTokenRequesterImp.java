package ichop.users.requesters;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.jms.token.delete.password.PasswordTokenDeleteByTokenRequest;
import ichop.users.domain.models.jms.token.retrieve.password.PasswordTokenFindByTokenRequest;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidRequest;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordTokenRequesterImp implements PasswordTokenRequester {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;

    private String createDestination;
    private String isValidDestination;
    private String findByTokenDestination;
    private String deleteByTokenDestination;

    @Autowired
    public PasswordTokenRequesterImp(JmsHelper jmsHelper,
                                     ObjectMapper objectMapper,
                                     @Value("${artemis.queue.tokens.password.create}") String createDestination,
                                     @Value("${artemis.queue.tokens.password.is_valid}") String isValidDestination,
                                     @Value("${artemis.queue.tokens.password.find.by.token}") String findByTokenDestination,
                                     @Value("${artemis.queue.tokens.password.delete.by.token}") String deleteByTokenDestination) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;

        this.createDestination = createDestination;
        this.isValidDestination = isValidDestination;
        this.findByTokenDestination = findByTokenDestination;
        this.deleteByTokenDestination = deleteByTokenDestination;
    }


    @Override
    public JmsReplyModel create(PasswordTokenCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request);
    }

    @Override
    public JmsReplyModel isValid(PasswordTokenIsValidRequest request) {
        return this.jmsHelper.sendAndReceive(this.isValidDestination, request);
    }

    @Override
    public boolean isValid(String token) {
        JmsReplyModel result = this.jmsHelper.sendAndReceive(this.isValidDestination, new PasswordTokenIsValidRequest(token));
        BoolReply reply = this.objectMapper.convertValue(result.getData(), BoolReply.class);

        return reply != null ? reply.getResult() : false;
    }

    @Override
    public JmsReplyModel findByToken(String token) {
        PasswordTokenFindByTokenRequest request = new PasswordTokenFindByTokenRequest(token);

        return this.jmsHelper.sendAndReceive(this.findByTokenDestination, request);
    }

    @Override
    public JmsReplyModel deleteByToken(PasswordTokenDeleteByTokenRequest request) {
        return this.jmsHelper.sendAndReceive(this.deleteByTokenDestination, request);
    }
}
