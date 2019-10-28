package ichop.users.requesters;

import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateReply;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidReply;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordTokenRequesterImp implements PasswordTokenRequester {

    private final JmsHelper jmsHelper;

    private String createDestination;
    private String isValidDestination;

    @Autowired
    public PasswordTokenRequesterImp(JmsHelper jmsHelper,
                                     @Value("${artemis.queue.tokens.password.create}") String createDestination,
                                     @Value("${artemis.queue.tokens.password.is_valid}") String isValidDestination) {
        this.jmsHelper = jmsHelper;

        this.createDestination = createDestination;
        this.isValidDestination = isValidDestination;
    }


    @Override
    public PasswordTokenCreateReply create(PasswordTokenCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request, PasswordTokenCreateReply.class);
    }

    @Override
    public PasswordTokenIsValidReply isValid(PasswordTokenIsValidRequest request) {
        return this.jmsHelper.sendAndReceive(this.isValidDestination, request, PasswordTokenIsValidReply.class);
    }
}
