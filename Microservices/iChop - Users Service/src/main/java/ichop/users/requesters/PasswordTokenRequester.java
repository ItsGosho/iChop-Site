package ichop.users.requesters;


import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateReply;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidReply;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidRequest;

public interface PasswordTokenRequester {

    PasswordTokenCreateReply create(PasswordTokenCreateRequest request);
    PasswordTokenIsValidReply isValid(PasswordTokenIsValidRequest request);

}
