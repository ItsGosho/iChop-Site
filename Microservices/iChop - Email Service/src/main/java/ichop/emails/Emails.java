package ichop.emails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ichop.emails","org.ichop.commons"})
public class Emails {
    public static void main(String[] args) {
        SpringApplication.run(Emails.class, args);
    }
}