package ichop.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@ComponentScan({"ichop.core","org.ichop.commons"})
public class Core {
    public static void main(String[] args) {
        SpringApplication.run(Core.class, args);
    }
}