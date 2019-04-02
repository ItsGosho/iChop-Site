package com.ichop.basicstatistics.components;

import com.ichop.basicstatistics.domain.models.jms.receive.GetPlayerBasicStatisticsByUUIDJmsReceiveModel;
import com.ichop.basicstatistics.domain.models.jms.returnn.BaseJMSReturnModel;
import com.ichop.basicstatistics.domain.models.jms.returnn.GetPlayerBasicStatisticsByUUIDJmsReturnModel;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Map;

public interface JmsServices {


    @SuppressWarnings("unchecked")
    <ReturnModel> ReturnModel getJmsModel(Message message, Class<ReturnModel> clazz) throws JMSException;

    Map<String, Object> messageToHashMap(Message message);

    Message convertValuesIntoMessage(Map<String, Object> values);

    Message returnResultModel(Object model);

    Message returnErrorModel(Object jmsModel, Class<? extends BaseJMSReturnModel> getPlayerBasicStatisticsByUUIDJmsReturnModelClass);
}
