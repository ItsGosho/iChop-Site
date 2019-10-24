package ichop.core.areas.player.services;

import ichop.core.areas.jms.services.JmsServices;
import ichop.core.areas.player.domain.jms.key.receive.PlayerDataByKeyJMSReceiveModel;
import ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import ichop.core.areas.player.domain.jms.key.send.PlayerDataByKeyJMSSendModel;
import ichop.core.areas.player.domain.jms.key.send.PlayerDataBySiteUserJMSSendModel;
import ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import ichop.core.areas.player.domain.jms.player.link.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import ichop.core.areas.player.domain.jms.player.link.receive.LinkPlayerAccountJMSReceiveModel;
import ichop.core.areas.player.domain.jms.player.link.receive.UnlinkPlayerAccountJMSReceiveModel;
import ichop.core.areas.player.domain.jms.player.link.send.GetPlayerDataByPlayerUUIDJMSSendModel;
import ichop.core.areas.player.domain.jms.player.link.send.IsPlayerLinkedAccountByUUIDJMSSendModel;
import ichop.core.areas.player.domain.jms.player.link.send.LinkPlayerAccountJMSSendModel;
import ichop.core.areas.player.domain.jms.player.link.send.UnlinkPlayerAccountJMSSendModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ichop.core.areas.player.constants.LinkAccountJMSConstants.*;

@Service
public class PlayerLinkJmsServicesImp implements PlayerLinkJmsServices {

    private final JmsServices jmsServices;

    @Autowired
    public PlayerLinkJmsServicesImp(JmsServices jmsServices) {
        this.jmsServices = jmsServices;
    }

    @Override
    public IsPlayerLinkedAccountByUUIDJMSReceiveModel isPlayerLinkedAccountByUUID(String uuid) {
        IsPlayerLinkedAccountByUUIDJMSSendModel sendModel = new IsPlayerLinkedAccountByUUIDJMSSendModel();
        sendModel.setUuid(uuid);

        return this.jmsServices.sendAndReceiveModel(sendModel,IsPlayerLinkedAccountByUUIDJMSReceiveModel.class,IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION);
    }

    @Override
    public PlayerDataByKeyJMSReceiveModel getPlayerDataByLinkKey(String key) {
        PlayerDataByKeyJMSSendModel sendModel = new PlayerDataByKeyJMSSendModel();
        sendModel.setKey(key);

        return this.jmsServices.sendAndReceiveModel(sendModel,PlayerDataByKeyJMSReceiveModel.class,GET_PLAYER_DATA_BY_LINK_KEY_DESTINATION);
    }

    @Override
    public PlayerDataBySiteUserJMSReceiveModel getPlayerDataBySiteUser(String siteUserUsername) {

        PlayerDataBySiteUserJMSSendModel sendModel = new PlayerDataBySiteUserJMSSendModel();
        sendModel.setSiteUserUsername(siteUserUsername);

        return this.jmsServices.sendAndReceiveModel(sendModel,PlayerDataBySiteUserJMSReceiveModel.class,GET_PLAYER_DATA_BY_SITE_USER_DESTINATION);
    }

    @Override
    public GetPlayerDataByPlayerUUIDJMSReceiveModel getPlayerDataByPlayerUUID(String uuid) {

        GetPlayerDataByPlayerUUIDJMSSendModel sendModel = new GetPlayerDataByPlayerUUIDJMSSendModel();
        sendModel.setUuid(uuid);

        return this.jmsServices.sendAndReceiveModel(sendModel,GetPlayerDataByPlayerUUIDJMSReceiveModel.class,GET_PLAYER_DATA_BY_PLAYER_UUID);
    }

    @Override
    public LinkPlayerAccountJMSReceiveModel sendSiteUserToPlayerLinkConnection(String key, String userUsername) {

        LinkPlayerAccountJMSSendModel sendModel = new LinkPlayerAccountJMSSendModel();
        sendModel.setKey(key);
        sendModel.setSiteUserUsername(userUsername);

        return this.jmsServices.sendAndReceiveModel(sendModel,LinkPlayerAccountJMSReceiveModel.class,LINK_PLAYER_ACCOUNT_DESTINATION);
    }

    @Override
    public void unlinkPlayerAccount(String userUsername) {

        UnlinkPlayerAccountJMSSendModel sendModel = new UnlinkPlayerAccountJMSSendModel();
        sendModel.setSiteUserUsername(userUsername);

        this.jmsServices.sendAndReceiveModel(sendModel, UnlinkPlayerAccountJMSReceiveModel.class,UNLINK_PLAYER_ACCOUNT_DESTINATION);
    }

}
