package ichop.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@ComponentScan({"ichop.link","org.ichop.commons"})
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-common.properties"),
})
public class LinkAccount {
    public static void main(String[] args) {
        SpringApplication.run(LinkAccount.class, args);
    }
}