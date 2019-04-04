package com.ichop.core.areas.player.constants;

import org.springframework.stereotype.Component;

@Component
public final class LinkAccountJMSConstants {

    private static final String PREFIX = "ichop_link_account-";

    public static final String IS_PLAYER_LINKED_ACCOUNT_BY_UUID_DESTINATION  = PREFIX + "is_player_linked_account_by_uuid";
    public static final String GET_PLAYER_DATA_BY_LINK_KEY_DESTINATION = PREFIX + "get_player_data_by_key";
    public static final String GET_PLAYER_DATA_BY_SITE_USER_DESTINATION = PREFIX + "get_player_data_by_site_user";
    public static final String GET_PLAYER_DATA_BY_PLAYER_UUID = PREFIX + "get_player_data_by_player_uuid";
    public static final String LINK_PLAYER_ACCOUNT_DESTINATION = PREFIX + "link_player_account";
}
