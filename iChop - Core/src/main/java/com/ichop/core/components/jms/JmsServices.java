package com.ichop.core.components.jms;

import com.ichop.core.base.BaseJMSReceiveModel;
import com.ichop.core.base.BaseJMSSendModel;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

public interface JmsServices {


    @SuppressWarnings("unchecked")
    <ReceiveModel extends BaseJMSReceiveModel> ReceiveModel getResultModel(Map<String,Object> values, Class<ReceiveModel> clazz);

    Map<String, Object> messageToHashMap(Message message);

    <ReceiveModel extends BaseJMSReceiveModel,SendModel extends BaseJMSSendModel> ReceiveModel sendAndReceiveModel(SendModel sendModel, Class<ReceiveModel> receiveModel, String destination);

    <SendModel extends BaseJMSSendModel> void sendModel(SendModel sendModel, String destination);

    Map<String,Object> sendAndReceive(String destinationName, HashMap<String, Object> values);

    MessageCreator convertMessageIntoMessageCreator(Message message);

    Message convertValuesIntoMessage(HashMap<String, Object> values);
}
