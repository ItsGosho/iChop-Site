package com.ichop.core.areas.player.services;

import com.ichop.core.areas.player.domain.jms.player.basicstatistics.receive.GetPlayerBasicStatisticsByUUIDJMSReceiveModel;

public interface PlayerBasicStatisticServices {

    /*
    *
    * Get the player statistic data by in-game player uuid.In case of
    * not existing data ,a null will be returned.
    *
    * */
    GetPlayerBasicStatisticsByUUIDJMSReceiveModel getPlayerDataByUUID(String uuid);

}
