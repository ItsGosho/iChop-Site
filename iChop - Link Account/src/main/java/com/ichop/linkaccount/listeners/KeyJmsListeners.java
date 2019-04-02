package com.ichop.linkaccount.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.components.JmsServices;
import com.ichop.linkaccount.components.ValidationUtil;
import com.ichop.linkaccount.domain.models.jms.key.receive.GetPlayerDataByKeyJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.key.receive.IsPlayerLinkKeyValidJMSReceiveModel;
import com.ichop.linkaccount.domain.models.jms.key.returnn.GetPlayerDataByKeyJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.key.returnn.IsPlayerLinkKeyValidJMSReturnModel;
import com.ichop.linkaccount.domain.models.jms.playerlink.returnn.GetPlayerDataByPlayerUUIDJMSReturnModel;
import com.ichop.linkaccount.domain.models.service.KeyServiceModel;
import com.ichop.linkaccount.listeners.annotations.key.GetPlayerDataByKeyListener;
import com.ichop.linkaccount.services.KeyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.jms.JMSException;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;
import static com.ichop.linkaccount.constants.JmsConstants.*;


@Component
public class KeyJmsListeners extends BaseJmsListener {

    private final KeyServices keyServices;

    @Autowired
    public KeyJmsListeners(JmsServices jmsServices, ObjectMapper objectMapper, ValidationUtil validationUtil, KeyServices keyServices) {
        super(jmsServices, objectMapper, validationUtil);
        this.keyServices = keyServices;
    }


    @GetPlayerDataByKeyListener
    public Message getPlayerDataByKey(Message message) throws JMSException {
        GetPlayerDataByKeyJMSReceiveModel receivedJMSModel = super.jmsServices.getJmsModel(message,GetPlayerDataByKeyJMSReceiveModel.class);

        if(super.validationUtil.validate(receivedJMSModel).hasErrors()){
            return super.jmsServices.returnErrorModel(receivedJMSModel, GetPlayerDataByKeyJMSReturnModel.class);
        }

        KeyServiceModel keyServiceModel = this.keyServices.getByKey(receivedJMSModel.getKey());
        GetPlayerDataByKeyJMSReturnModel resultModel = super.objectMapper.convertValue(keyServiceModel,GetPlayerDataByKeyJMSReturnModel.class);

        HashMap<String, Object> resultValues = new HashMap<>();
        resultValues.put(SENDING_MODEL_PARAMETER_NAME,super.objectMapper.convertValue(resultModel,Map.class));
        return super.jmsServices.convertValuesIntoMessage(resultValues);
    }

}
