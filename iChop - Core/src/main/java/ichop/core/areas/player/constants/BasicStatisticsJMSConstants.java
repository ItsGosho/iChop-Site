package ichop.core.areas.player.constants;

import org.springframework.stereotype.Component;

@Component
public final class BasicStatisticsJMSConstants {

    private static final String PREFIX = "ichop_basic_statistics-";

    public static final String GET_PLAYER_DATA_BY_UUID = PREFIX + "get_player_data_by_uuid";
}
