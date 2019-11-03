package ichop.users.requesters;

import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.jms.token.delete.password.PasswordTokenDeleteByTokenRequest;
import ichop.users.domain.models.jms.token.retrieve.password.PasswordTokenFindByTokenRequest;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordTokenRequesterImp implements PasswordTokenRequester {

    private final JmsHelper jmsHelper;

    private String createDestination;
    private String isValidDestination;
    private String findByTokenDestination;
    private String deleteByTokenDestination;

    @Autowired
    public PasswordTokenRequesterImp(JmsHelper jmsHelper,
                                     @Value("${artemis.queue.tokens.password.create}") String createDestination,
                                     @Value("${artemis.queue.tokens.password.is_valid}") String isValidDestination,
                                     @Value("${artemis.queue.tokens.password.find.by.token}") String findByTokenDestination,
                                     @Value("${artemis.queue.tokens.password.delete.by.token}") String deleteByTokenDestination) {
        this.jmsHelper = jmsHelper;

        this.createDestination = createDestination;
        this.isValidDestination = isValidDestination;
        this.findByTokenDestination = findByTokenDestination;
        this.deleteByTokenDestination = deleteByTokenDestination;
    }


    @Override
    public PasswordTokenCreateReply create(PasswordTokenCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request, PasswordTokenCreateReply.class);
    }

    @Override
    public PasswordTokenIsValidReply isValid(PasswordTokenIsValidRequest request) {
        return this.jmsHelper.sendAndReceive(this.isValidDestination, request, PasswordTokenIsValidReply.class);
    }

    @Override
    public boolean isValid(String token) {
        PasswordTokenIsValidReply result = this.jmsHelper.sendAndReceive(this.isValidDestination,
                new PasswordTokenIsValidRequest(token),
                PasswordTokenIsValidReply.class);
        return result.isValid();
    }

    @Override
    public PasswordTokenFindByTokenReply findByToken(PasswordTokenFindByTokenRequest request) {
        return this.jmsHelper.sendAndReceive(this.findByTokenDestination, request, PasswordTokenFindByTokenReply.class);
    }

    @Override
    public PasswordTokenDeleteByTokenReply deleteByToken(PasswordTokenDeleteByTokenRequest request) {
        return this.jmsHelper.sendAndReceive(this.deleteByTokenDestination, request, PasswordTokenDeleteByTokenReply.class);
    }
}
