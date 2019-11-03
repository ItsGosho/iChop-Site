package ichop.tokens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"ichop.tokens","org.ichop.commons"})
public class Tokens {
    public static void main(String[] args) {
        SpringApplication.run(Tokens.class, args);
    }
}