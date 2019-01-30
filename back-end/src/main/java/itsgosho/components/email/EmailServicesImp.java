package itsgosho.components.email;

import itsgosho.utils.GoshoTemplatingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class EmailServicesImp implements EmailServices {


    private final JavaMailSender emailSender;

    @Autowired
    public EmailServicesImp(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    public void sendEmailFromTemplate(String to, String subject, Map<String, String> properties, String resourceLocation) {
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            GoshoTemplatingEngine tempEngine = new GoshoTemplatingEngine
                    .TemEngineBuilder()
                    .setViewLocation(resourceLocation)
                    .setProperties(properties)
                    .build();

            helper.setText(tempEngine.getResult(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        this.emailSender.send(message);
    }

    @Override
    public void sendResetPasswordEmail(String userEmail, String token) {
        //TODO: make constants for the validaity seconds,url !!!!!!!!!!!!!
        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("tokenValidityInHours", "24");
        properties.put("passwordResetUrl", "http://localhost:8000/users/reset/password?token=" + token);

        this.sendEmailFromTemplate(userEmail, "Reset password", properties, "email/reset-password.html");
    }
}
