package com.ichop.core.areas.player.services;

import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataByKeyJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.key.receive.PlayerDataBySiteUserJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.GetPlayerDataByPlayerUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.IsPlayerLinkedAccountByUUIDJMSReceiveModel;
import com.ichop.core.areas.player.domain.jms.player.link.receive.LinkPlayerAccountJMSReceiveModel;


public interface PlayerLinkServices {

    /*
    *
    * Get if the in-game player is already linked his account
    * by the account uuid.
    * In case of not existing data ,a null
    * will be returned;
    *
    * */
    IsPlayerLinkedAccountByUUIDJMSReceiveModel isPlayerLinkedAccountByUUID(String uuid);


    /*
    *
    * Get the player data by existing link-key.
    * In case of not existing link-key ,a null
    * will be returned.
    *
    * */
    PlayerDataByKeyJMSReceiveModel getPlayerDataByLinkKey(String key);

    /*
    *
    * Get the player data ,by already created link-account record,
    * where the siteUserUsername is the username of the user in
    * the site.
    * In case of not existing link-account record,a null will be
    * returned.
    *
    * */
    PlayerDataBySiteUserJMSReceiveModel getPlayerDataBySiteUser(String siteUserUsername);

    /*
    *
    * Get the player data ,by already created link-account record,where the
    * uuid parameter is representing the in-game player uuid.
    * In case of not existing link-account record,a null will be returned.
    *
    * */
    GetPlayerDataByPlayerUUIDJMSReceiveModel getPlayerDataByPlayerUUID(String uuid);

    /*
    *
    * Link the player account by already existing link-account key,where the userUsername
    * is present too.
    * In case of not existing key ,a null will be returned.
    *
    * */
    LinkPlayerAccountJMSReceiveModel sendSiteUserToPlayerLinkConnection(String key, String userUsername);

}
