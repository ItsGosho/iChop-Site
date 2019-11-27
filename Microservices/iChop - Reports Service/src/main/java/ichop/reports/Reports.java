package ichop.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan({"ichop.reports","org.ichop.commons"})
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-common.properties"),
})
public class Reports {
    public static void main(String[] args) {
        SpringApplication.run(Reports.class, args);
    }
}