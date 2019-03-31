package com.ichop.basicstatistics.components;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Map;

public interface JmsServices {


    @SuppressWarnings("unchecked")
    <ReturnModel> ReturnModel getJmsModel(Message message, Class<ReturnModel> clazz) throws JMSException;

    Map<String, Object> messageToHashMap(Message message);

    Message convertValuesIntoMessage(Map<String, Object> values);

    Message returnErrors(Object model);
    Message returnResultModel(Object model);

}
