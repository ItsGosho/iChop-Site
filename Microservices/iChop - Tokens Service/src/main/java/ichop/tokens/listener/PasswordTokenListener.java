package ichop.tokens.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.common.aop.JmsAfterReturn;
import ichop.tokens.common.aop.JmsValidate;
import ichop.tokens.common.helpers.BaseListener;
import ichop.tokens.common.helpers.JmsHelper;
import ichop.tokens.domain.models.jms.create.password.PasswordTokenCreateReply;
import ichop.tokens.domain.models.jms.create.password.PasswordTokenCreateRequest;
import ichop.tokens.domain.models.jms.delete.password.PasswordTokenDeleteByTokenReply;
import ichop.tokens.domain.models.jms.delete.password.PasswordTokenDeleteByTokenRequest;
import ichop.tokens.domain.models.jms.retrieve.password.PasswordTokenFindByTokenReply;
import ichop.tokens.domain.models.jms.retrieve.password.PasswordTokenFindByTokenRequest;
import ichop.tokens.domain.models.jms.valid.password.PasswordTokenIsValidReply;
import ichop.tokens.domain.models.jms.valid.password.PasswordTokenIsValidRequest;
import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import ichop.tokens.services.PasswordTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.tokens.common.constants.JmsFactories.QUEUE;
import static ichop.tokens.constants.TokenReplyConstants.*;

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
        passwordToken.setToken(this.passwordTokenServices.generateToken());

        this.passwordTokenServices.deleteAllByUser(passwordToken.getUserId());
        PasswordTokenCreateReply reply = this.passwordTokenServices.save(passwordToken, PasswordTokenCreateReply.class);
        return reply;
    }

    @JmsValidate(model = PasswordTokenIsValidRequest.class)
    @JmsAfterReturn(message = TOKEN_RETRIEVED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.is_valid}", containerFactory = QUEUE)
    public PasswordTokenIsValidReply isValid(Message message) {
        PasswordTokenIsValidRequest requestModel = this.jmsHelper.getResultModel(message, PasswordTokenIsValidRequest.class);

        boolean isValid = this.passwordTokenServices.isValid(requestModel.getToken());

        return new PasswordTokenIsValidReply(isValid);
    }

    @JmsValidate(model = PasswordTokenFindByTokenRequest.class)
    @JmsAfterReturn(message = TOKEN_RETRIEVED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.find.by.token}", containerFactory = QUEUE)
    public PasswordTokenFindByTokenReply findByToken(Message message) {
        PasswordTokenFindByTokenRequest requestModel = this.jmsHelper.getResultModel(message, PasswordTokenFindByTokenRequest.class);

        PasswordTokenServiceModel token = this.passwordTokenServices.findByToken(requestModel.getToken());

        return this.objectMapper.convertValue(token, PasswordTokenFindByTokenReply.class);
    }

    @JmsValidate(model = PasswordTokenDeleteByTokenRequest.class)
    @JmsAfterReturn(message = TOKEN_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.delete.by.token}", containerFactory = QUEUE)
    public PasswordTokenDeleteByTokenReply deleteByToken(Message message) {
        PasswordTokenDeleteByTokenRequest requestModel = this.jmsHelper.getResultModel(message, PasswordTokenDeleteByTokenRequest.class);

        this.passwordTokenServices.deleteByToken(requestModel.getToken());

        return new PasswordTokenDeleteByTokenReply();
    }

}
