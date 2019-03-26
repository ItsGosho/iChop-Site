package com.ichop.core.service.player;

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

@Service
public class PlayerServicesImp implements PlayerServices {

    private static final String IS_PLAYER_LINK_KEY_VALID_DESTINATION = "ichop_link_account-is_player_link_key_valid";
    private static final String IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER_DESTINATION = "ichop_link_account-is_player_linked_account_by_site_user";
    private static final String IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION = "ichop_link_account-is_player_linked_account_by_uuid";
    private static final String GET_PLAYER_DATA_BY_LINK_KEY = "ichop_link_account-get_player_data_by_key";
    private static final String GET_PLAYER_DATA_BY_SITE_USER = "ichop_link_account-get_player_data_by_site_user";
    private static final String LINK_PLAYER_ACCOUNT_DESTINATION = "ichop_link_account-link_player_account";

    private final JmsServices jmsServices;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public PlayerServicesImp(JmsServices jmsServices, ModelMapper modelMapper, ObjectMapper objectMapper) {
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
        IsPlayerLinkKeyValidJMSReturnModel returnModel = this.modelMapper.map(result.get("resultModel"),IsPlayerLinkKeyValidJMSReturnModel.class);

        return returnModel.isValid();
    }

    @Override
    public boolean isPlayerLinkedAccountBySiteUser(String siteUserUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();

        IsPlayerLinkedAccountBySiteUserJMSSendModel sendModel = new IsPlayerLinkedAccountBySiteUserJMSSendModel();
        sendModel.setSiteUserUsername(siteUserUsername);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER_DESTINATION, valuesToSend);
        IsPlayerLinkedAccountBySiteUserJMSReturnModel returnModel = this.modelMapper.map(result.get("resultModel"),IsPlayerLinkedAccountBySiteUserJMSReturnModel.class);

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

        Map<String,Object> result = this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_LINK_KEY, valuesToSend);
        PlayerDataByKeyJMSReturnModel returnModel = this.modelMapper.map(result.get("resultModel"), PlayerDataByKeyJMSReturnModel.class);

        return returnModel;
    }

    @Override
    public PlayerDataBySiteUserJMSReturnModel getPlayerDataBySiteUser(String siteUserUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        PlayerDataBySiteUserJMSSendModel sendModel = new PlayerDataBySiteUserJMSSendModel();
        sendModel.setSiteUserUsername(siteUserUsername);
        valuesToSend.put("jmsModel", this.objectMapper.convertValue(sendModel,Map.class));

        Map<String,Object> result = this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_SITE_USER, valuesToSend);
        PlayerDataBySiteUserJMSReturnModel returnModel = this.modelMapper.map(result.get("resultModel"), PlayerDataBySiteUserJMSReturnModel.class);

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
        LinkPlayerAccountJMSReturnModel linkPlayerAccountJMSReturnModel = this.modelMapper.map(result.get("resultModel"),LinkPlayerAccountJMSReturnModel.class);

        return linkPlayerAccountJMSReturnModel.isSuccessful();
    }

}
