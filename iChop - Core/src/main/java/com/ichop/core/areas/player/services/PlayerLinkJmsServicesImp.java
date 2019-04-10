package com.ichop.core.areas.player.services;

import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataByKeyJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.key.send.PlayerDataByKeyJMSSendModel;
import com.ichop.core.areas.player.domain.jms.key.send.PlayerDataBySiteUserJMSSendModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.LinkPlayerAccountJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.UnlinkPlayerAccountJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.send.GetPlayerDataByPlayerUUIDJMSSendModel;
import com.ichop.core.areas.player.domain.jms.player.link.send.IsPlayerLinkedAccountByUUIDJMSSendModel;
import com.ichop.core.areas.player.domain.jms.player.link.send.LinkPlayerAccountJMSSendModel;
import com.ichop.core.areas.player.domain.jms.player.link.send.UnlinkPlayerAccountJMSSendModel;
import com.ichop.core.components.jms.JmsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.ichop.core.areas.player.constants.LinkAccountJMSConstants.*;

@Service
@CacheConfig(cacheNames = "players")
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

    @Cacheable
    @Override
    public PlayerDataByKeyJMSReceiveModel getPlayerDataByLinkKey(String key) {
        PlayerDataByKeyJMSSendModel sendModel = new PlayerDataByKeyJMSSendModel();
        sendModel.setKey(key);

        return this.jmsServices.sendAndReceiveModel(sendModel,PlayerDataByKeyJMSReceiveModel.class,GET_PLAYER_DATA_BY_LINK_KEY_DESTINATION);
    }

    @Cacheable
    @Override
    public PlayerDataBySiteUserJMSReceiveModel getPlayerDataBySiteUser(String siteUserUsername) {

        PlayerDataBySiteUserJMSSendModel sendModel = new PlayerDataBySiteUserJMSSendModel();
        sendModel.setSiteUserUsername(siteUserUsername);

        return this.jmsServices.sendAndReceiveModel(sendModel,PlayerDataBySiteUserJMSReceiveModel.class,GET_PLAYER_DATA_BY_SITE_USER_DESTINATION);
    }

    @Cacheable
    @Override
    public GetPlayerDataByPlayerUUIDJMSReceiveModel getPlayerDataByPlayerUUID(String uuid) {

        GetPlayerDataByPlayerUUIDJMSSendModel sendModel = new GetPlayerDataByPlayerUUIDJMSSendModel();
        sendModel.setUuid(uuid);

        return this.jmsServices.sendAndReceiveModel(sendModel,GetPlayerDataByPlayerUUIDJMSReceiveModel.class,GET_PLAYER_DATA_BY_PLAYER_UUID);
    }

    @CacheEvict(allEntries = true)
    @Override
    public LinkPlayerAccountJMSReceiveModel sendSiteUserToPlayerLinkConnection(String key, String userUsername) {

        LinkPlayerAccountJMSSendModel sendModel = new LinkPlayerAccountJMSSendModel();
        sendModel.setKey(key);
        sendModel.setSiteUserUsername(userUsername);

        return this.jmsServices.sendAndReceiveModel(sendModel,LinkPlayerAccountJMSReceiveModel.class,LINK_PLAYER_ACCOUNT_DESTINATION);
    }

    @CacheEvict(allEntries = true)
    @Override
    public void unlinkPlayerAccount(String userUsername) {

        UnlinkPlayerAccountJMSSendModel sendModel = new UnlinkPlayerAccountJMSSendModel();
        sendModel.setSiteUserUsername(userUsername);

        this.jmsServices.sendAndReceiveModel(sendModel, UnlinkPlayerAccountJMSReceiveModel.class,UNLINK_PLAYER_ACCOUNT_DESTINATION);
    }

}
