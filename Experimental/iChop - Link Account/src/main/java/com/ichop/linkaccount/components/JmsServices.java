package com.ichop.linkaccount.components;

import com.ichop.linkaccount.domain.models.jms.BaseJMSReturnModel;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Map;

public interface JmsServices {


    Map<String, Object> sendAndReceive(String destinationName, Map<String, Object> values);

    @SuppressWarnings("unchecked")
    <ReturnModel> ReturnModel getJmsModel(Message message, Class<ReturnModel> clazz) throws JMSException;

    Map<String, Object> messageToHashMap(Message message);

    Message convertValuesIntoMessage(Map<String, Object> values);

    Message returnResultModel(Object model);

    Message returnErrorModel(Object jmsModel, Class<? extends BaseJMSReturnModel> getPlayerBasicStatisticsByUUIDJmsReturnModelClass);
}
