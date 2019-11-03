package ichop.emails.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.emails.constants.SubjectConstants;
import ichop.emails.domain.models.jms.EmailResetPasswordRequest;
import ichop.emails.helpers.FreemakerHelper;
import ichop.emails.helpers.JavaMailHelper;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.EmptyReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.emails.constants.EmailReplyConstants.EMAIL_SENT_SUCCESSFUL;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

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
        EmailResetPasswordRequest requestModel = this.jmsHelper.toModel(message, EmailResetPasswordRequest.class);

        try {
            String html = this.freemakerHelper.prepareResetPasswordView(requestModel.getExpirationDate(), requestModel.getToken());
            this.javaMailHelper.send(requestModel.getTo(), SubjectConstants.RESET_PASSWORD, html);

            this.jmsHelper.replySuccessful(message, new EmptyReply(), EMAIL_SENT_SUCCESSFUL);
        } catch (Exception e) {
            this.jmsHelper.replyError(message, new EmptyReply(), e.getMessage());
        }
    }

}
