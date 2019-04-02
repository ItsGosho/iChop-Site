package com.ichop.core.areas.player.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataByKeyJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.key.send.PlayerDataByKeyJMSSendModel;
import com.ichop.core.areas.player.domain.jms.key.send.PlayerDataBySiteUserJMSSendModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.LinkPlayerAccountJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.send.GetPlayerDataByPlayerUUIDJMSSendModel;
import com.ichop.core.areas.player.domain.jms.player.link.send.IsPlayerLinkedAccountByUUIDJMSSendModel;
import com.ichop.core.areas.player.domain.jms.player.link.send.LinkPlayerAccountJMSSendModel;
import com.ichop.core.components.jms.JmsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.ichop.core.areas.player.constants.LinkAccountJMSConstants.*;

@Service
public class PlayerLinkServicesImp implements PlayerLinkServices {

    private final JmsServices jmsServices;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlayerLinkServicesImp(JmsServices jmsServices, ObjectMapper objectMapper) {
        this.jmsServices = jmsServices;
        this.objectMapper = objectMapper;
    }

    @Override
    public IsPlayerLinkedAccountByUUIDJMSReceiveModel isPlayerLinkedAccountByUUID(String uuid) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        IsPlayerLinkedAccountByUUIDJMSSendModel sendModel = new IsPlayerLinkedAccountByUUIDJMSSendModel();
        sendModel.setUuid(uuid);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel, Map.class));

        IsPlayerLinkedAccountByUUIDJMSReceiveModel result = this.jmsServices
                .getResultModel(this.jmsServices.sendAndReceive(IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION, valuesToSend),IsPlayerLinkedAccountByUUIDJMSReceiveModel.class);

        return result;
    }

    @Override
    public PlayerDataByKeyJMSReceiveModel getPlayerDataByLinkKey(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        PlayerDataByKeyJMSSendModel sendModel = new PlayerDataByKeyJMSSendModel();
        sendModel.setKey(key);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel, Map.class));

        PlayerDataByKeyJMSReceiveModel result = this.jmsServices
                .getResultModel(this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_LINK_KEY_DESTINATION, valuesToSend),PlayerDataByKeyJMSReceiveModel.class);

        return result;
    }

    @Override
    public PlayerDataBySiteUserJMSReceiveModel getPlayerDataBySiteUser(String siteUserUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        PlayerDataBySiteUserJMSSendModel sendModel = new PlayerDataBySiteUserJMSSendModel();
        sendModel.setSiteUserUsername(siteUserUsername);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel, Map.class));

        PlayerDataBySiteUserJMSReceiveModel result = this.jmsServices
                .getResultModel(this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_SITE_USER_DESTINATION, valuesToSend),PlayerDataBySiteUserJMSReceiveModel.class);

        return result;
    }

    @Override
    public GetPlayerDataByPlayerUUIDJMSReceiveModel getPlayerDataByPlayerUUID(String uuid) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        GetPlayerDataByPlayerUUIDJMSSendModel sendModel = new GetPlayerDataByPlayerUUIDJMSSendModel();
        sendModel.setUuid(uuid);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel, Map.class));

        GetPlayerDataByPlayerUUIDJMSReceiveModel result = this.jmsServices
                .getResultModel(this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_PLAYER_UUID, valuesToSend),GetPlayerDataByPlayerUUIDJMSReceiveModel.class);

        return result;
    }

    @Override
    public LinkPlayerAccountJMSReceiveModel sendSiteUserToPlayerLinkConnection(String key, String userUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        LinkPlayerAccountJMSSendModel sendModel = new LinkPlayerAccountJMSSendModel();
        sendModel.setKey(key);
        sendModel.setSiteUserUsername(userUsername);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel, Map.class));

        LinkPlayerAccountJMSReceiveModel result = this.jmsServices
                .getResultModel(this.jmsServices.sendAndReceive(LINK_PLAYER_ACCOUNT_DESTINATION, valuesToSend),LinkPlayerAccountJMSReceiveModel.class);
        return result;
    }

}
