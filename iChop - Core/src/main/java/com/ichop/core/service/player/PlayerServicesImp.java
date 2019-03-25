package com.ichop.core.service.player;

import com.ichop.core.components.jms.JmsServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerServicesImp implements PlayerServices {

    private static final String IS_PLAYER_LINK_KEY_VALID_DESTINATION = "ichop_minecraft-is_player_link_key_valid";
    private static final String IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER_DESTINATION = "ichop_minecraft-is_player_linked_account_by_site_user";
    private static final String IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION = "ichop_minecraft-is_player_linked_account_by_uuid";
    private static final String GET_PLAYER_DATA_BY_LINK_KEY = "ichop_minecraft-get_player_data_by_key";
    private static final String GET_PLAYER_DATA_BY_SITE_USER = "ichop_minecraft-get_player_data_by_site_user";
    private static final String LINK_PLAYER_ACCOUNT_DESTINATION = "ichop_minecraft-link_player_account";

    private final JmsServices jmsServices;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerServicesImp(JmsServices jmsServices, ModelMapper modelMapper) {
        this.jmsServices = jmsServices;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isPlayerLinkKeyValid(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("key", key);

        Map<String,Object> result = this.jmsServices.sendAndReceive(IS_PLAYER_LINK_KEY_VALID_DESTINATION, valuesToSend);

        return (boolean) result.get("isValid");
    }

    @Override
    public boolean isPlayerLinkedAccountBySiteUser(String siteUserUsername) {

        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("siteUserUsername", siteUserUsername);

        Map<String,Object> result = this.jmsServices.sendAndReceive(IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER_DESTINATION, valuesToSend);

        return (boolean) result.get("isLinked");
    }

    @Override
    public boolean isPlayerLinkedAccountByUUID(String uuid) {

        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("uuid", uuid);

        Map<String,Object> result = this.jmsServices.sendAndReceive(IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION, valuesToSend);

        return (boolean) result.get("isLinked");
    }

    @Override
    public Map<String,Object> getPlayerDataByLinkKey(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("key", key);

        return this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_LINK_KEY, valuesToSend);
    }

    @Override
    public Map<String, Object> getPlayerDataBySiteUser(String siteUserUsername) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("siteUserUsername", siteUserUsername);

        return this.jmsServices.sendAndReceive(GET_PLAYER_DATA_BY_SITE_USER, valuesToSend);
    }

    @Override
    public boolean sendSiteUserToPlayerLinkConnection(String playerName,String playerUUID,String userUsername) {

        HashMap<String, Object> values = new HashMap<>();
        values.put("playerName", playerName);
        values.put("playerUUID", playerUUID);
        values.put("siteUserUsername", userUsername);

        Map<String,Object> result = this.jmsServices.sendAndReceive(LINK_PLAYER_ACCOUNT_DESTINATION,values);

        return (boolean) result.get("isSuccessful");
    }

}
