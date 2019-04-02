package com.ichop.core.areas.player.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class LinkAccountJMSConstants {

    public static String IS_PLAYER_LINK_KEY_VALID_DESTINATION;
    public static String IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER_DESTINATION;
    public static String IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION ;
    public static String GET_PLAYER_DATA_BY_LINK_KEY_DESTINATION;
    public static String GET_PLAYER_DATA_BY_SITE_USER_DESTINATION;
    public static String GET_PLAYER_DATA_BY_PLAYER_UUID;
    public static String LINK_PLAYER_ACCOUNT_DESTINATION;


    @Value("${jms.listener.name.link_account.is_player_link_key_valid}")
    private void setIsPlayerLinkKeyValidDestination(String isPlayerLinkKeyValidDestination) {
        IS_PLAYER_LINK_KEY_VALID_DESTINATION = isPlayerLinkKeyValidDestination;
    }

    @Value("${jms.listener.name.link_account.is_player_linked_account_by_site_user}")
    private void setIsPlayerLinkedAccountBySiteUserDestination(String isPlayerLinkedAccountBySiteUserDestination) {
        IS_PLAYER_LINKED_ACCOUNT_BY_SITE_USER_DESTINATION = isPlayerLinkedAccountBySiteUserDestination;
    }

    @Value("${jms.listener.name.link_account.is_player_linked_account_by_uuid}")
    private void setIsPlayerLinkedAccountByUuidDestination(String isPlayerLinkedAccountByUuidDestination) {
        IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION = isPlayerLinkedAccountByUuidDestination;
    }

    @Value("${jms.listener.name.link_account.get_player_data_by_key}")
    private void setGetPlayerDataByLinkKeyDestination(String getPlayerDataByLinkKeyDestination) {
        GET_PLAYER_DATA_BY_LINK_KEY_DESTINATION = getPlayerDataByLinkKeyDestination;
    }

    @Value("${jms.listener.name.link_account.get_player_data_by_site_user}")
    private void setGetPlayerDataBySiteUserDestination(String getPlayerDataBySiteUserDestination) {
        GET_PLAYER_DATA_BY_SITE_USER_DESTINATION = getPlayerDataBySiteUserDestination;
    }

    @Value("${jms.listener.name.link_account.get_player_data_by_player_uuid}")
    public void setGetPlayerDataByPlayerUuid(String getPlayerDataByPlayerUuid) {
        GET_PLAYER_DATA_BY_PLAYER_UUID = getPlayerDataByPlayerUuid;
    }

    @Value("${jms.listener.name.link_account.link_player_account}")
    private void setLinkPlayerAccountDestination(String linkPlayerAccountDestination) {
        LINK_PLAYER_ACCOUNT_DESTINATION = linkPlayerAccountDestination;
    }
}
