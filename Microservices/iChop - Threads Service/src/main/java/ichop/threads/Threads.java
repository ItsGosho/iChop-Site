package ichop.threads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
@ComponentScan({"org.ichop.commons"})
public class Threads {
    public static void main(String[] args) {
        SpringApplication.run(Threads.class, args);
    }
}