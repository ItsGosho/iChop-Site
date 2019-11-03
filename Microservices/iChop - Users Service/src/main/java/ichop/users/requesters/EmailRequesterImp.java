package ichop.users.requesters;

import ichop.users.common.helpers.JmsHelper;
import ichop.users.domain.models.jms.email.EmailResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailRequesterImp implements EmailRequester {

    private final JmsHelper jmsHelper;

    private String passwordResetDestination;

    @Autowired
    public EmailRequesterImp(JmsHelper jmsHelper, @Value("${artemis.queue.emails.reset_password}") String passwordResetDestination) {
        this.jmsHelper = jmsHelper;

        this.passwordResetDestination = passwordResetDestination;
    }


    @Override
    public EmailReply sendPasswordReset(EmailResetPasswordRequest request) {
        return this.jmsHelper.sendAndReceive(this.passwordResetDestination,request,EmailReply.class);
    }
}
