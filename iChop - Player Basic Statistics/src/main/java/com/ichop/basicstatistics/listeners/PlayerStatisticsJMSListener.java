package com.ichop.basicstatistics.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.basicstatistics.components.JmsServices;
import com.ichop.basicstatistics.components.ValidationUtil;
import com.ichop.basicstatistics.domain.models.jms.receive.GetPlayerBasicStatisticsByUUIDJmsReceiveModel;
import com.ichop.basicstatistics.domain.models.jms.returnn.GetPlayerBasicStatisticsByUUIDJmsReturnModel;
import com.ichop.basicstatistics.domain.models.service.PlayerStatisticsServiceModel;
import com.ichop.basicstatistics.listeners.annotations.GetPlayerDataByUUIDListener;
import com.ichop.basicstatistics.services.PlayerStatisticsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;

import static com.ichop.basicstatistics.constants.JmsConstants.*;

@Component
public class PlayerStatisticsJMSListener extends BaseJmsListener {

    private final PlayerStatisticsServices playerStatisticsServices;

    @Autowired
    protected PlayerStatisticsJMSListener(JmsServices jmsServices, ObjectMapper objectMapper, ValidationUtil validationUtil, PlayerStatisticsServices playerStatisticsServices) {
        super(jmsServices, objectMapper, validationUtil);
        this.playerStatisticsServices = playerStatisticsServices;
    }


    @GetPlayerDataByUUIDListener
    public Message getPlayerDataByUUID(Message message) throws JMSException {

        GetPlayerBasicStatisticsByUUIDJmsReceiveModel receivedJMSModel = super.jmsServices.getJmsModel(message, GetPlayerBasicStatisticsByUUIDJmsReceiveModel.class);

        if (super.validationUtil.validate(receivedJMSModel).hasErrors()) {
            return super.jmsServices.returnErrors(receivedJMSModel);
        }

        PlayerStatisticsServiceModel result = this.playerStatisticsServices.findByUUID(receivedJMSModel.getUuid());
        GetPlayerBasicStatisticsByUUIDJmsReturnModel resultModel = result != null ? super.objectMapper.convertValue(result, GetPlayerBasicStatisticsByUUIDJmsReturnModel.class) : null;

        return super.jmsServices.returnResultModel(resultModel);
    }


}
