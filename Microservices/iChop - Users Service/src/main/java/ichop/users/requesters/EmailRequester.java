package ichop.users.requesters;


import ichop.users.domain.models.jms.email.EmailResetPasswordRequest;

public interface EmailRequester {

    EmailReply sendPasswordReset(EmailResetPasswordRequest request);

}
