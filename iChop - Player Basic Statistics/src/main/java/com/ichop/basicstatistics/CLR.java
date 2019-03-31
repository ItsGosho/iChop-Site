package com.ichop.basicstatistics;

import com.ichop.basicstatistics.components.ValidationUtilImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final ValidationUtilImp validationUtilImp;

    @Autowired
    public CLR(ValidationUtilImp validationUtilImp) {
        this.validationUtilImp = validationUtilImp;
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
