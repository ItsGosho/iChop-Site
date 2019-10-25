package ichop.emails.helpers;

import ichop.emails.domain.models.JavaMailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class JavaMailHelperImp implements JavaMailHelper {

    private final JavaMailSender javaMailSender;

    @Autowired
    public JavaMailHelperImp(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void send(JavaMailModel javaMailModel) throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(javaMailModel.getTo());
        helper.setFrom(javaMailModel.getFrom());
        helper.setSubject(javaMailModel.getSubject());
        helper.setText(javaMailModel.getHtmlContent(), true);

        this.javaMailSender.send(message);
    }

}
