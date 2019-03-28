package com.ichop.core.service.player.link;

import com.ichop.core.domain.models.jms.key.returnn.PlayerDataByKeyJMSReturnModel;
import com.ichop.core.domain.models.jms.key.returnn.PlayerDataBySiteUserJMSReturnModel;

public interface PlayerLinkServices {

    boolean isPlayerLinkKeyValid(String key);
    boolean isPlayerLinkedAccountBySiteUser(String siteUserUsername);
    boolean isPlayerLinkedAccountByUUID(String uuid);
    PlayerDataByKeyJMSReturnModel getPlayerDataByLinkKey(String key);
    PlayerDataBySiteUserJMSReturnModel getPlayerDataBySiteUser(String siteUserUsername);

    boolean sendSiteUserToPlayerLinkConnection(String playerName,String playerUUID,String userUsername);

}
