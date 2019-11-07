package ichop.reactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ichop.reactions","org.ichop.commons"})
public class Reactions {
    public static void main(String[] args) {
        SpringApplication.run(Reactions.class, args);
    }
}