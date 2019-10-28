package ichop.emails.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class JavaMailHelperImp implements JavaMailHelper {

    private final JavaMailSender javaMailSender;


    @Value("${email.sending_email}")
    private String sendingEmail;

    @Autowired
    public JavaMailHelperImp(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void send(String to,String subject,String htmlContent) throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setFrom(this.sendingEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        this.javaMailSender.send(message);
    }

}
