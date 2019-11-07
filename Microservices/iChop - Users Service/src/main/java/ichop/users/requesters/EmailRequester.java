package ichop.users.requesters;


import ichop.users.domain.models.jms.email.EmailResetPasswordRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface EmailRequester {

    JmsReplyModel sendPasswordReset(EmailResetPasswordRequest request);

}
