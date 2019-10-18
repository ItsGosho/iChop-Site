package ichop.tokens.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.tokens.common.aop.JmsAfterReturn;
import ichop.tokens.common.aop.JmsValidate;
import ichop.tokens.common.helpers.JmsHelper;
import ichop.tokens.domain.models.jms.password.create.PasswordTokenCreateReplyModel;
import ichop.tokens.domain.models.jms.password.create.PasswordTokenCreateRequestModel;
import ichop.tokens.domain.models.jms.password.valid.PasswordTokenIsValidReplyModel;
import ichop.tokens.domain.models.jms.password.valid.PasswordTokenIsValidRequestModel;
import ichop.tokens.domain.models.service.PasswordTokenServiceModel;
import ichop.tokens.services.PasswordTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

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


    @JmsValidate(model = PasswordTokenCreateRequestModel.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.token.password.create}", containerFactory = "queueFactory")
    public PasswordTokenCreateReplyModel create(Message message) {
        PasswordTokenCreateRequestModel requestModel = this.jmsHelper.getResultModel(message, PasswordTokenCreateRequestModel.class);

        PasswordTokenServiceModel passwordToken = this.objectMapper.convertValue(requestModel, PasswordTokenServiceModel.class);

        PasswordTokenCreateReplyModel replyModel = this.passwordTokenServices.save(passwordToken, PasswordTokenCreateReplyModel.class);
        replyModel.setMessage(TOKEN_CREATED_SUCCESSFUL);

        return replyModel;
    }

    @JmsValidate(model = PasswordTokenIsValidRequestModel.class)
    @JmsAfterReturn
    @JmsListener(destination = "${artemis.queue.token.password.is_valid}", containerFactory = "queueFactory")
    public PasswordTokenIsValidReplyModel isValid(Message message) {
        PasswordTokenIsValidRequestModel requestModel = this.jmsHelper.getResultModel(message, PasswordTokenIsValidRequestModel.class);

        boolean isValid = this.passwordTokenServices.isValid(requestModel.getToken());

        PasswordTokenIsValidReplyModel replyModel = new PasswordTokenIsValidReplyModel();
        replyModel.setValid(isValid);
        replyModel.setMessage(TOKEN_RETRIEVED_SUCCESSFUL);

        return replyModel;
    }
}
