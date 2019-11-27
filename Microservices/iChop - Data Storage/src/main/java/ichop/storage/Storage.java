package ichop.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableCaching
@ComponentScan({"ichop.storage","org.ichop.commons"})
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-common.properties"),
})
public class Storage {
    public static void main(String[] args) {
        SpringApplication.run(Storage.class, args);
    }
}