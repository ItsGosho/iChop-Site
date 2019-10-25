package ichop.emails.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.emails.common.aop.JmsValidate;
import ichop.emails.common.helpers.BaseListener;
import ichop.emails.common.helpers.JmsHelper;
import ichop.emails.constants.SubjectConstants;
import ichop.emails.domain.models.jms.EmailReply;
import ichop.emails.domain.models.jms.EmailResetPasswordRequest;
import ichop.emails.helpers.FreemakerHelper;
import ichop.emails.helpers.JavaMailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.emails.common.constants.JmsFactories.QUEUE;
import static ichop.emails.constants.EmailReplyConstants.EMAIL_SENT_SUCCESSFUL;

@Component
public class EmailListeners extends BaseListener {

    private final JavaMailHelper javaMailHelper;
    private final FreemakerHelper freemakerHelper;


    @Autowired
    protected EmailListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, JavaMailHelper javaMailHelper, FreemakerHelper freemakerHelper) {
        super(jmsHelper, objectMapper);
        this.javaMailHelper = javaMailHelper;
        this.freemakerHelper = freemakerHelper;
    }


    @JmsValidate(model = EmailResetPasswordRequest.class)
    @JmsListener(destination = "${artemis.queue.emails.reset_password}", containerFactory = QUEUE)
    public void create(Message message) {
        EmailResetPasswordRequest requestModel = this.jmsHelper.getResultModel(message, EmailResetPasswordRequest.class);

        try {
            String html = this.freemakerHelper.prepareResetPasswordView(requestModel.getExpirationDate(), requestModel.getTo());
            this.javaMailHelper.send(requestModel.getTo(), SubjectConstants.RESET_PASSWORD, html);

            this.jmsHelper.replySuccessful(message, new EmailReply(), EMAIL_SENT_SUCCESSFUL);
        } catch (Exception e) {
            this.jmsHelper.replyFailed(message, new EmailReply(), e.getMessage());
        }
    }

}
