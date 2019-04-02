package com.ichop.core.areas.player.services;

import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataByKeyJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.LinkPlayerAccountJMSReceiveModel;

public interface PlayerLinkServices {

    IsPlayerLinkedAccountByUUIDJMSReceiveModel isPlayerLinkedAccountByUUID(String uuid);
    PlayerDataByKeyJMSReceiveModel getPlayerDataByLinkKey(String key);
    PlayerDataBySiteUserJMSReceiveModel getPlayerDataBySiteUser(String siteUserUsername);
    GetPlayerDataByPlayerUUIDJMSReceiveModel getPlayerDataByPlayerUUID(String uuid);

    LinkPlayerAccountJMSReceiveModel sendSiteUserToPlayerLinkConnection(String key, String userUsername);

}
