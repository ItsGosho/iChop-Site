package ichop.users.requesters;


import ichop.users.domain.models.jms.email.EmailReply;
import ichop.users.domain.models.jms.email.EmailResetPasswordRequest;

public interface EmailRequester {

    EmailReply sendPasswordReset(EmailResetPasswordRequest request);

}
