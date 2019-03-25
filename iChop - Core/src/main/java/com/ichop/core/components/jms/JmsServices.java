package com.ichop.core.components.jms;

import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

public interface JmsServices {


    Map<String, Object> messageToHashMap(Message message);

    Map<String,Object> sendAndReceive(String destinationName, HashMap<String, Object> values);

    Message convertValuesIntoMessage(HashMap<String, Object> values);
}
