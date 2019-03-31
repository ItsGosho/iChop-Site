package com.ichop.linkaccount.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.components.JmsServices;
import com.ichop.linkaccount.components.ValidationUtil;

public abstract class BaseJmsListener {

    protected final JmsServices jmsServices;
    protected final ObjectMapper objectMapper;
    protected final ValidationUtil validationUtil;

    protected BaseJmsListener(JmsServices jmsServices, ObjectMapper objectMapper, ValidationUtil validationUtil) {
        this.jmsServices = jmsServices;
        this.objectMapper = objectMapper;
        this.validationUtil = validationUtil;
    }
}
