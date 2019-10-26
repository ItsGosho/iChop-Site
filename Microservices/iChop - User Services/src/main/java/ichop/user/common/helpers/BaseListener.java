package ichop.user.common.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseListener {

    protected final JmsHelper jmsHelper;
    protected final ObjectMapper objectMapper;


    protected BaseListener(JmsHelper jmsHelper, ObjectMapper objectMapper) {
        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;
    }
}