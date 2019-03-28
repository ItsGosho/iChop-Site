package com.ichop.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;


    @Autowired
    public CLR(ModelMapper modelMapper, ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
