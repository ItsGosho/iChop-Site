package com.ichop.core;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final ModelMapper modelMapper;

    @Autowired
    public CLR(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {


    }


}
