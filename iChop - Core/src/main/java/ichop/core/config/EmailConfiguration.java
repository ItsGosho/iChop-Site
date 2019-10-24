package ichop.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private Integer port;

    @Value("${email.email}")
    private String email;

    @Value("${email.password}")
    private String password;

    @Value("${email.transport.protocol}")
    private String transportProtocol;

    @Value("${email.smtp.auth}")
    private String smtpAuth;

    @Value("${email.smtp.starttls.enable}")
    private String smtpStarttls;

    @Value("${email.smtp.starttls.debug}")
    private String smtpStarttlsDebug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.email);
        mailSender.setPassword(this.password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", this.transportProtocol);
        props.put("mail.smtp.auth", this.smtpAuth);
        props.put("mail.smtp.starttls.enable", this.smtpStarttls);
        props.put("mail.debug", this.smtpStarttlsDebug);

        return mailSender;
    }

}
