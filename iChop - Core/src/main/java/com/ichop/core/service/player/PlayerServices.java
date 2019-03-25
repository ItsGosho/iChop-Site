package com.ichop.core.service.player;

import java.util.Map;

public interface PlayerServices {

    boolean isPlayerLinkKeyValid(String key);
    boolean isPlayerLinkedAccountBySiteUser(String siteUserUsername);
    boolean isPlayerLinkedAccountByUUID(String uuid);
    Map<String,Object> getPlayerDataByLinkKey(String key);
    Map<String,Object> getPlayerDataBySiteUser(String siteUserUsername);

    boolean sendSiteUserToPlayerLinkConnection(String playerName,String playerUUID,String userUsername);

}
