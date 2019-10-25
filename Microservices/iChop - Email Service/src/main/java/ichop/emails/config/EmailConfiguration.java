package ichop.emails.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static ichop.emails.constants.ConditionalConstants.EMAIL_CONFIGURATION;

@Configuration
@ConditionalOnProperty(
        name = EMAIL_CONFIGURATION,
        havingValue = "true",
        matchIfMissing = true
)
public class EmailConfiguration {

    @Value("${email.host}")
    private String host;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.port}")
    private Integer port;

    @Value("${email.transport.protocol}")
    private String transportProtocol;

    @Value("${email.debug}")
    private Boolean debug;


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.host);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);
        mailSender.setPort(this.port);
        mailSender.setProtocol(this.transportProtocol);

        Properties properties = new Properties();
        properties.setProperty("mail.debug", String.valueOf(this.debug));
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

}