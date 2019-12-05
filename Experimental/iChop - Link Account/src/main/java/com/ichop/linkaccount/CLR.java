package com.ichop.linkaccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.components.JmsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Component
public class CLR implements CommandLineRunner {

    private final JmsServices jmsServices;
    private final ObjectMapper objectMapper;

    @Autowired
    public CLR(JmsServices jmsServices, ObjectMapper objectMapper) {
        this.jmsServices = jmsServices;
        this.objectMapper = objectMapper;
    }


    @Override
    public void run(String... args) throws Exception {

    }

}
