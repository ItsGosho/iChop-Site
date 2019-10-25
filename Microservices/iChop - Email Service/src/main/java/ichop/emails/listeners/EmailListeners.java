package ichop.emails.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.emails.common.aop.JmsValidate;
import ichop.emails.common.helpers.BaseListener;
import ichop.emails.common.helpers.JmsHelper;
import ichop.emails.constants.TemplatePathConstants;
import ichop.emails.domain.models.JavaMailModel;
import ichop.emails.domain.models.jms.EmailReply;
import ichop.emails.domain.models.jms.EmailResetPasswordRequest;
import ichop.emails.domain.models.templates.EmailResetPasswordView;
import ichop.emails.helpers.FreemakerHelper;
import ichop.emails.helpers.JavaMailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.emails.common.constants.JmsFactories.QUEUE;
import static ichop.emails.constants.EmailReplyConstants.EMAIL_SENT_SUCCESSFUL;

@Component
public class EmailListeners extends BaseListener {

    private final FreemakerHelper freemakerHelper;
    private final JavaMailHelper javaMailHelper;

    @Value("${url.password_reset_token}")
    private String tokenResetUrl;


    @Autowired
    protected EmailListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, FreemakerHelper freemakerHelper, JavaMailHelper javaMailHelper) {
        super(jmsHelper, objectMapper);
        this.freemakerHelper = freemakerHelper;
        this.javaMailHelper = javaMailHelper;
    }


    @JmsValidate(model = EmailResetPasswordRequest.class)
    @JmsListener(destination = "${artemis.queue.emails.reset_password}", containerFactory = QUEUE)
    public void create(Message message) {

        try {
            EmailResetPasswordRequest requestModel = this.jmsHelper.getResultModel(message, EmailResetPasswordRequest.class);
            String html = this.prepareResetPasswordView(requestModel);

            this.javaMailHelper.send(requestModel.getTo(),"Reset your password!",html);
            this.jmsHelper.replySuccessful(message, new EmailReply(), EMAIL_SENT_SUCCESSFUL);
        } catch (Exception e) {
            this.jmsHelper.replyFailed(message, new EmailReply(), e.getMessage());
        }
    }

    private String prepareResetPasswordView(EmailResetPasswordRequest request) throws Exception {
        String expirationDate = request.getExpirationDate().toString();

        EmailResetPasswordView view = new EmailResetPasswordView();
        view.setExpirationDate(expirationDate);
        view.setPasswordResetUrl(this.tokenResetUrl + request.getToken());

        return this.freemakerHelper.proceed(TemplatePathConstants.RESET_PASSWORD, view);
    }

}
