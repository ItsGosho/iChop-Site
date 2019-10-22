package ichop.tokens.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.common.aop.JmsAfterReturn;
import ichop.tokens.common.aop.JmsValidate;
import ichop.tokens.common.helpers.JmsHelper;
import ichop.tokens.domain.models.jms.password.create.PasswordTokenCreateReply;
import ichop.tokens.domain.models.jms.password.create.PasswordTokenCreateRequest;
import ichop.tokens.domain.models.jms.password.valid.PasswordTokenIsValidReply;
import ichop.tokens.domain.models.jms.password.valid.PasswordTokenIsValidRequest;
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
public class PasswordTokenListener {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;
    private final PasswordTokenServices passwordTokenServices;

    @Autowired
    public PasswordTokenListener(JmsHelper jmsHelper, ObjectMapper objectMapper, PasswordTokenServices passwordTokenServices) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
        this.passwordTokenServices = passwordTokenServices;
    }


    @JmsValidate(model = PasswordTokenCreateRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.token.password.create}", containerFactory = QUEUE)
    public PasswordTokenCreateReply create(Message message) {
        PasswordTokenCreateRequest requestModel = this.jmsHelper.getResultModel(message, PasswordTokenCreateRequest.class);

        PasswordTokenServiceModel passwordToken = this.objectMapper.convertValue(requestModel, PasswordTokenServiceModel.class);

        PasswordTokenCreateReply replyModel = this.passwordTokenServices.save(passwordToken, PasswordTokenCreateReply.class);
        replyModel.setMessage(TOKEN_CREATED_SUCCESSFUL);

        return replyModel;
    }

    @JmsValidate(model = PasswordTokenIsValidRequest.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.token.password.is_valid}", containerFactory = QUEUE)
    public PasswordTokenIsValidReply isValid(Message message) {
        PasswordTokenIsValidRequest requestModel = this.jmsHelper.getResultModel(message, PasswordTokenIsValidRequest.class);

        boolean isValid = this.passwordTokenServices.isValid(requestModel.getToken());

        PasswordTokenIsValidReply replyModel = new PasswordTokenIsValidReply();
        replyModel.setValid(isValid);
        replyModel.setMessage(TOKEN_RETRIEVED_SUCCESSFUL);

        return replyModel;
    }
}
