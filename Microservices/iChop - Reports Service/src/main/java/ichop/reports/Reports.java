package ichop.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ichop.reports","org.ichop.commons"})
public class Reports {
    public static void main(String[] args) {
        SpringApplication.run(Reports.class, args);
    }
}