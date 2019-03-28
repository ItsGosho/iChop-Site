package com.ichop.core.service.player.link;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.core.components.jms.JmsServices;
import com.ichop.core.domain.models.jms.key.returnn.PlayerDataByKeyJMSReturnModel;
import com.ichop.core.domain.models.jms.key.returnn.PlayerDataBySiteUserJMSReturnModel;
import com.ichop.core.domain.models.jms.key.returnn.IsPlayerLinkKeyValidJMSReturnModel;
import com.ichop.core.domain.models.jms.key.send.PlayerDataByKeyJMSSendModel;
import com.ichop.core.domain.models.jms.key.send.PlayerDataBySiteUserJMSSendModel;
import com.ichop.core.domain.models.jms.key.send.IsPlayerLinkKeyValidJMSSendModel;
import com.ichop.core.domain.models.jms.player.link.returnn.IsPlayerLinkedAccountBySiteUserJMSReturnModel;
import com.ichop.core.domain.models.jms.player.link.returnn.IsPlayerLinkedAccountByUUIDJMSReturnModel;
import com.ichop.core.domain.models.jms.player.link.returnn.LinkPlayerAccountJMSReturnModel;
import com.ichop.core.domain.models.jms.player.link.send.IsPlayerLinkedAccountBySiteUserJMSSendModel;
import com.ichop.core.domain.models.jms.player.link.send.IsPlayerLinkedAccountByUUIDJMSSendModel;
import com.ichop.core.domain.models.jms.player.link.send.LinkPlayerAccountJMSSendModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.ichop.core.constants.LinkAccountJMSConstants.*;

@Service
public class PlayerLinkServicesImp implements PlayerLinkServices {

    private final JmsServices jmsServices;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlayerLinkServicesImp(JmsServices jmsServices, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.jmsServices = jmsServices;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isPlayerLinkKeyValid(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        IsPlayerLinkKeyValidJMSSendModel sendModel = new IsPlayerLinkKeyValidJMSSendModel();
        sendModel.setKey(key);
        valuesToSend.put("jmsModel",this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(IS_PLAYER_LINK_KEY_VALID_DESTINATION, valuesToSend);
        IsPlayerLinkKeyValidJMSReturnModel returnModel =
                result.get("resultModel") != null ? this.modelMapper.map(result.get("resultModel"), IsPlayerLinkKeyValidJMSReturnModel.class) : null;

        return returnModel.isValid();
    }

    @Override
    public boolean isPlayerLinkedAccountBySiteUser(String siteUserUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        IsPlayerLinkedAccountBySiteUserJMSSendModel sendModel = new IsPlayerLinkedAccountBySiteUserJMSSendModel();
        sendModel.setSiteUserUsername(siteUserUsername);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER_DESTINATION, valuesToSend);
        IsPlayerLinkedAccountBySiteUserJMSReturnModel returnModel =
                result.get("resultModel") != null ? this.modelMapper.map(result.get("resultModel"), IsPlayerLinkedAccountBySiteUserJMSReturnModel.class) : null;

        return returnModel.isLinked();
    }

    @Override
    public boolean isPlayerLinkedAccountByUUID(String uuid) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        IsPlayerLinkedAccountByUUIDJMSSendModel sendModel = new IsPlayerLinkedAccountByUUIDJMSSendModel();
        sendModel.setUuid(uuid);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION, valuesToSend);
        IsPlayerLinkedAccountByUUIDJMSReturnModel returnModel = this.modelMapper.map(result.get("result"),IsPlayerLinkedAccountByUUIDJMSReturnModel.class);

        return returnModel.isLinked();
    }

    @Override
    public PlayerDataByKeyJMSReturnModel getPlayerDataByLinkKey(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        PlayerDataByKeyJMSSendModel sendModel = new PlayerDataByKeyJMSSendModel();
        sendModel.setKey(key);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_LINK_KEY_DESTINATION, valuesToSend);
        PlayerDataByKeyJMSReturnModel returnModel =
                result.get("resultModel") != null ? this.modelMapper.map(result.get("resultModel"), PlayerDataByKeyJMSReturnModel.class) : null;

        return returnModel;
    }

    @Override
    public PlayerDataBySiteUserJMSReturnModel getPlayerDataBySiteUser(String siteUserUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        PlayerDataBySiteUserJMSSendModel sendModel = new PlayerDataBySiteUserJMSSendModel();
        sendModel.setSiteUserUsername(siteUserUsername);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_SITE_USER_DESTINATION, valuesToSend);
        PlayerDataBySiteUserJMSReturnModel returnModel =
                result.get("resultModel") != null ? this.modelMapper.map(result.get("resultModel"), PlayerDataBySiteUserJMSReturnModel.class) : null;

        return returnModel;
    }

    @Override
    public boolean sendSiteUserToPlayerLinkConnection(String playerName,String playerUUID,String userUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        LinkPlayerAccountJMSSendModel sendModel = new LinkPlayerAccountJMSSendModel();
        sendModel.setPlayerName(playerName);
        sendModel.setPlayerUUID(playerUUID);
        sendModel.setSiteUserUsername(userUsername);
        valuesToSend.put("jmsModel",this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(LINK_PLAYER_ACCOUNT_DESTINATION,valuesToSend);
        LinkPlayerAccountJMSReturnModel linkPlayerAccountJMSReturnModel =
                result.get("resultModel") != null ? this.modelMapper.map(result.get("resultModel"), LinkPlayerAccountJMSReturnModel.class) : null;

        return linkPlayerAccountJMSReturnModel.isSuccessful();
    }

}
