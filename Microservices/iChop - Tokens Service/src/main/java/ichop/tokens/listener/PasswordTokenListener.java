package ichop.tokens.listener;

import ichop.tokens.domain.models.jms.password.create.PasswordTokenCreateReplyModel;
import ichop.tokens.domain.models.jms.password.valid.PasswordTokenIsValidReplyModel;

import javax.jms.Message;

public interface PasswordTokenListener {

    PasswordTokenCreateReplyModel create(Message message);
    PasswordTokenIsValidReplyModel isValid(Message message);

}
