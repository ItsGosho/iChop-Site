package ichop.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ichop.comments","org.ichop.commons"})
public class Comments {
    public static void main(String[] args) {
        SpringApplication.run(Comments.class, args);
    }
}