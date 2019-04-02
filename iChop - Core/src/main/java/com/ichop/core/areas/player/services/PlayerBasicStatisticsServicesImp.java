package com.ichop.core.areas.player.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.core.areas.player.domain.jms.player.basicstatistics.receive.GetPlayerBasicStatisticsByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.basicstatistics.send.GetPlayerBasicStatisticsByUUIDJMSSendModel;
import com.ichop.core.components.jms.JmsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.ichop.core.areas.player.constants.BasicStatisticsJMSConstants.GET_PLAYER_DATA_BY_UUID;

@Service
public class PlayerBasicStatisticsServicesImp implements PlayerBasicStatisticServices {

    private final JmsServices jmsServices;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlayerBasicStatisticsServicesImp(JmsServices jmsServices, ObjectMapper objectMapper) {
        this.jmsServices = jmsServices;
        this.objectMapper = objectMapper;
    }


    @Override
    public GetPlayerBasicStatisticsByUUIDJMSReceiveModel getPlayerDataByUUID(String uuid) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        GetPlayerBasicStatisticsByUUIDJMSSendModel sendModel = new GetPlayerBasicStatisticsByUUIDJMSSendModel();
        sendModel.setUuid(uuid);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel, Map.class));

        GetPlayerBasicStatisticsByUUIDJMSReceiveModel result = this.jmsServices
                .getResultModel(this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_UUID, valuesToSend),GetPlayerBasicStatisticsByUUIDJMSReceiveModel.class);

        return result;
    }

}
