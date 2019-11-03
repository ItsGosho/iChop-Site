package ichop.users.requesters;


import ichop.users.domain.models.jms.token.create.password.PasswordTokenCreateRequest;
import ichop.users.domain.models.jms.token.delete.password.PasswordTokenDeleteByTokenRequest;
import ichop.users.domain.models.jms.token.valid.password.PasswordTokenIsValidRequest;
import org.ichop.commons.domain.JmsReplyModel;

public interface PasswordTokenRequester {

    JmsReplyModel create(PasswordTokenCreateRequest request);
    JmsReplyModel isValid(PasswordTokenIsValidRequest request);

    boolean isValid(String token);

    JmsReplyModel findByToken(String token);
    JmsReplyModel deleteByToken(PasswordTokenDeleteByTokenRequest request);

}
