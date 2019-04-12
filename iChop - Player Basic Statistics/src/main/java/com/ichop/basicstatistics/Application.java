package com.ichop.basicstatistics;

import com.ichop.basicstatistics.constants.ConsoleMessages;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(ConsoleMessages.APPLICATION_STARTED);
    }
}