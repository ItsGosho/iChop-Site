package ichop.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class Authentication {
    public static void main(String[] args) {
        SpringApplication.run(Authentication.class, args);
    }
}