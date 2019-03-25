package com.ichop.core.components.jms;

import java.util.Map;

public interface JmsServices {


    void sendUpdateAvatarRequest(String username,String imageAsBase64String);

    boolean isPlayerLinkKeyValid(String key);
    Map<String,Object> getPlayerDataByLinkKey(String key);

    boolean sendSiteUserToPlayerLinkConnection(String playerName,String playerUUID,String userUsername);

}
