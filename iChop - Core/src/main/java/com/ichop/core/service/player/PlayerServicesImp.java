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
    private static final String GET_PLAYER_UUID_BY_LINK_KEY_DESTINATION = "ichop_minecraft-get_player_data_by_key";
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

        boolean isValid = (boolean) result.get("isValid");

        return isValid;
    }

    @Override
    public Map<String,Object> getPlayerDataByLinkKey(String key) {
        HashMap<String, Object> valuesToSend = new HashMap<>();
        valuesToSend.put("key", key);

        Map<String,Object> result = this.jmsServices.sendAndReceive(GET_PLAYER_UUID_BY_LINK_KEY_DESTINATION, valuesToSend);

        return result;
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
