package ichop.core.areas.player.services;

import ichop.core.areas.jms.services.JmsServices;
import ichop.core.areas.player.domain.jms.player.basicstatistics.receive.GetPlayerBasicStatisticsByUUIDJMSReceiveModel;
import ichop.core.areas.player.domain.jms.player.basicstatistics.send.GetPlayerBasicStatisticsByUUIDJMSSendModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ichop.core.areas.player.constants.BasicStatisticsJMSConstants.GET_PLAYER_DATA_BY_UUID;

@Service
public class PlayerBasicStatisticsJmsServicesImp implements PlayerBasicStatisticJmsServices {

    private final JmsServices jmsServices;

    @Autowired
    public PlayerBasicStatisticsJmsServicesImp(JmsServices jmsServices) {
        this.jmsServices = jmsServices;
    }


    @Override
    public GetPlayerBasicStatisticsByUUIDJMSReceiveModel getPlayerDataByUUID(String uuid) {

        GetPlayerBasicStatisticsByUUIDJMSSendModel sendModel = new GetPlayerBasicStatisticsByUUIDJMSSendModel();
        sendModel.setUuid(uuid);

        return this.jmsServices.sendAndReceiveModel(sendModel,GetPlayerBasicStatisticsByUUIDJMSReceiveModel.class,GET_PLAYER_DATA_BY_UUID);
    }

}
