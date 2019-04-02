package com.ichop.core.areas.player.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class BasicStatisticsJMSConstants {

    public static String GET_PLAYER_DATA_BY_UUID;

    @Value("${jms.listener.name.basic_statistics.get_player_data_by_uuid}")
    public void setGetPlayerDataByUuid(String getPlayerDataByUuid) {
        GET_PLAYER_DATA_BY_UUID = getPlayerDataByUuid;
    }
}
