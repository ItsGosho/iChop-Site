package com.ichop.core.components.email;

import com.ichop.core.constants.ServerConstants;
import com.ichop.core.constants.URLConstants;
import com.ichop.core.utils.CustomTemplateEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class EmailServicesImp implements EmailServices {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServicesImp(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    private void sendEmailFromTemplate(String to, String subject, Map<String, String> properties, String resourceLocation) {
        MimeMessage message = this.emailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            CustomTemplateEngine tempEngine = CustomTemplateEngine
                    .builder()
                    .placeholder("${{VALUE}}")
                    .viewLocation(resourceLocation)
                    .properties(properties)
                    .proceedParameters()
                    .build();

            helper.setText(tempEngine.getResult(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        this.emailSender.send(message);
    }

    @Override
    public void sendResetPasswordEmail(String userEmail, String token, LocalDateTime tokenExpireDate) {
        String passwordResetUrl = String.format("%s://%s:%s%s?token=%s",
                ServerConstants.SERVER_PROTOCOL,
                ServerConstants.SEVER_DOMAIN,
                ServerConstants.SERVER_PORT,
                URLConstants.USER_RESET_PASSWORD_GET,
                token);

        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("expireDate",tokenExpireDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        properties.put("passwordResetUrl", passwordResetUrl);

        this.sendEmailFromTemplate(userEmail, "iChop - Reset Password", properties, "templates/email/reset-password.html");
    }
}
