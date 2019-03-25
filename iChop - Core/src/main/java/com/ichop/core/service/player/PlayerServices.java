package com.ichop.core.service.player;

import java.util.Map;

public interface PlayerServices {

    boolean isPlayerLinkKeyValid(String key);
    Map<String,Object> getPlayerDataByLinkKey(String key);

    boolean sendSiteUserToPlayerLinkConnection(String playerName,String playerUUID,String userUsername);

}
