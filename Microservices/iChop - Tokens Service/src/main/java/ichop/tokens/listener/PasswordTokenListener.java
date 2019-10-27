package ichop.tokens.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.common.aop.JmsAfterReturn;
import ichop.tokens.common.aop.JmsValidate;
import ichop.tokens.common.helpers.BaseListener;
import ichop.tokens.common.helpers.JmsHelper;
import ichop.tokens.domain.models.jms.create.password.PasswordTokenCreateReply;
import ichop.tokens.domain.models.jms.create.password.PasswordTokenCreateRequest;
import ichop.tokens.domain.models.jms.valid.password.PasswordTokenIsValidReply;
import ichop.tokens.domain.models.jms.valid.password.PasswordTokenIsValidRequest;
import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import ichop.tokens.services.PasswordTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.tokens.common.constants.JmsFactories.QUEUE;
import static ichop.tokens.constants.TokenReplyConstants.TOKEN_CREATED_SUCCESSFUL;
import static ichop.tokens.constants.TokenReplyConstants.TOKEN_RETRIEVED_SUCCESSFUL;

@Component
public class PasswordTokenListener extends BaseListener {

    private final PasswordTokenServices passwordTokenServices;

    @Autowired
    protected PasswordTokenListener(JmsHelper jmsHelper, ObjectMapper objectMapper, PasswordTokenServices passwordTokenServices) {
        super(jmsHelper, objectMapper);
        this.passwordTokenServices = passwordTokenServices;
    }


    @JmsValidate(model = PasswordTokenCreateRequest.class)
    @JmsAfterReturn(message = TOKEN_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.create}", containerFactory = QUEUE)
    public PasswordTokenCreateReply create(Message message) {
        PasswordTokenCreateRequest requestModel = this.jmsHelper.getResultModel(message, PasswordTokenCreateRequest.class);

        PasswordTokenServiceModel passwordToken = this.objectMapper.convertValue(requestModel, PasswordTokenServiceModel.class);

        this.passwordTokenServices.deleteAllByUser(passwordToken.getUserId());
        return this.passwordTokenServices.save(passwordToken, PasswordTokenCreateReply.class);
    }

    @JmsValidate(model = PasswordTokenIsValidRequest.class)
    @JmsAfterReturn(message = TOKEN_RETRIEVED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.is_valid}", containerFactory = QUEUE)
    public PasswordTokenIsValidReply isValid(Message message) {
        PasswordTokenIsValidRequest requestModel = this.jmsHelper.getResultModel(message, PasswordTokenIsValidRequest.class);

        boolean isValid = this.passwordTokenServices.isValid(requestModel.getToken());

        return new PasswordTokenIsValidReply(isValid);
    }
}
