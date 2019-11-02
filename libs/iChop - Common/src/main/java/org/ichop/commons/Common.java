package org.ichop.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class Common {
    public static void main(String[] args) {
        SpringApplication.run(Common.class, args);
    }
}
