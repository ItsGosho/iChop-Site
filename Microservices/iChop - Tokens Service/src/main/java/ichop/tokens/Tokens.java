package ichop.tokens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Tokens {
    public static void main(String[] args) {
        SpringApplication.run(Tokens.class, args);
    }
}