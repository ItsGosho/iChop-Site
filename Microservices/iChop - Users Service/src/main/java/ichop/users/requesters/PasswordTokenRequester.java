package ichop.users.requesters;


import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateReply;
import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.jms.token.delete.password.PasswordTokenDeleteByTokenReply;
import ichop.users.domain.models.jms.token.delete.password.PasswordTokenDeleteByTokenRequest;
import ichop.users.domain.models.jms.token.retrieve.password.PasswordTokenFindByTokenReply;
import ichop.users.domain.models.jms.token.retrieve.password.PasswordTokenFindByTokenRequest;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidReply;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidRequest;

public interface PasswordTokenRequester {

    PasswordTokenCreateReply create(PasswordTokenCreateRequest request);
    PasswordTokenIsValidReply isValid(PasswordTokenIsValidRequest request);
    PasswordTokenFindByTokenReply findByToken(PasswordTokenFindByTokenRequest request);
    PasswordTokenDeleteByTokenReply deleteByToken(PasswordTokenDeleteByTokenRequest request);

}
