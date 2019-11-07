package ichop.tokens.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.domain.models.jms.PasswordTokenReply;
import ichop.tokens.domain.models.jms.create.PasswordTokenCreateRequest;
import ichop.tokens.domain.models.jms.delete.PasswordTokenDeleteByTokenRequest;
import ichop.tokens.domain.models.jms.retrieve.PasswordTokenFindByTokenRequest;
import ichop.tokens.domain.models.jms.valid.PasswordTokenIsValidRequest;
import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import ichop.tokens.services.PasswordTokenServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.tokens.constants.TokenReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

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
    public PasswordTokenReply create(Message message) {
        PasswordTokenCreateRequest requestModel = this.jmsHelper.toModel(message, PasswordTokenCreateRequest.class);

        PasswordTokenServiceModel passwordToken = this.objectMapper.convertValue(requestModel, PasswordTokenServiceModel.class);
        passwordToken.setToken(this.passwordTokenServices.generateToken());

        this.passwordTokenServices.deleteAllByUserUsername(passwordToken.getUserUsername());
        return this.passwordTokenServices.save(passwordToken, PasswordTokenReply.class);
    }

    @JmsValidate(model = PasswordTokenIsValidRequest.class)
    @JmsAfterReturn(message = TOKEN_RETRIEVED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.is_valid}", containerFactory = QUEUE)
    public BoolReply isValid(Message message) {
        PasswordTokenIsValidRequest requestModel = this.jmsHelper.toModel(message, PasswordTokenIsValidRequest.class);

        boolean isValid = this.passwordTokenServices.isValid(requestModel.getToken());

        return new BoolReply(isValid);
    }

    @JmsValidate(model = PasswordTokenFindByTokenRequest.class)
    @JmsAfterReturn(message = TOKEN_RETRIEVED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.find.by.token}", containerFactory = QUEUE)
    public PasswordTokenReply findByToken(Message message) {
        PasswordTokenFindByTokenRequest requestModel = this.jmsHelper.toModel(message, PasswordTokenFindByTokenRequest.class);

        PasswordTokenServiceModel token = this.passwordTokenServices.findByToken(requestModel.getToken());

        return this.objectMapper.convertValue(token, PasswordTokenReply.class);
    }

    @JmsValidate(model = PasswordTokenDeleteByTokenRequest.class)
    @JmsAfterReturn(message = TOKEN_DELETED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.tokens.password.delete.by.token}", containerFactory = QUEUE)
    public EmptyReply deleteByToken(Message message) {
        PasswordTokenDeleteByTokenRequest requestModel = this.jmsHelper.toModel(message, PasswordTokenDeleteByTokenRequest.class);

        this.passwordTokenServices.deleteByToken(requestModel.getToken());

        return new EmptyReply();
    }

}
