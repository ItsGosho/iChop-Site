package com.ichop.core.areas.email.services;

import com.ichop.core.areas.email.domain.models.ReportsStatisticsDto;
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

import static com.ichop.core.constants.ServerConstants.*;
import static com.ichop.core.constants.URLConstants.*;

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
        String passwordResetUrl = String.format("%s%s?token=%s",
                SERVER_URL,
                USER_RESET_PASSWORD_GET,
                token);

        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("expireDate",tokenExpireDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        properties.put("passwordResetUrl", passwordResetUrl);

        this.sendEmailFromTemplate(userEmail, "iChop - Reset Password", properties, "templates/email/reset-password.html");
    }

    @Override
    public void sendReportStatisticsEmail(ReportsStatisticsDto reportsStatisticsDto) {
        Map<String, String> properties = new LinkedHashMap<>();
        properties.put("totalReports",Integer.toString(reportsStatisticsDto.getTotalReports()));
        properties.put("totalThreadReports", Integer.toString(reportsStatisticsDto.getTotalThreadReports()));
        properties.put("totalCommentReports", Integer.toString(reportsStatisticsDto.getTotalCommentReports()));
        properties.put("totalPostReports", Integer.toString(reportsStatisticsDto.getTotalPostReports()));
        properties.put("allThreadReportsLink", SERVER_URL + THREAD_REPORTS_ALL_GET);
        properties.put("allCommentReportsLink", SERVER_URL + COMMENT_REPORTS_ALL_GET);
        properties.put("allPostReportsLink", SERVER_URL + POST_REPORTS_ALL_GET);

        for (String email : reportsStatisticsDto.getEmails()) {
            this.sendEmailFromTemplate(email,"iChop - Reports Statistic",properties,"templates/email/reports-statistics.html");
        }
    }

}
