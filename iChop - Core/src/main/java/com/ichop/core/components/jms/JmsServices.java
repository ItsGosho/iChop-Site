package com.ichop.core.components.jms;

import com.ichop.core.base.BaseJMSReceiveModel;

import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

public interface JmsServices {


    @SuppressWarnings("unchecked")
    <ReceiveModel extends BaseJMSReceiveModel> ReceiveModel getResultModel(Map<String,Object> values, Class<ReceiveModel> clazz);

    Map<String, Object> messageToHashMap(Message message);

    Map<String,Object> sendAndReceive(String destinationName, HashMap<String, Object> values);

    Message convertValuesIntoMessage(HashMap<String, Object> values);
}
