package com.ichop.core.areas.player.services;

import com.ichop.core.areas.player.domain.jms.player.basicstatistics.receive.GetPlayerBasicStatisticsByUUIDJMSReceiveModel;

public interface PlayerBasicStatisticServices {

    GetPlayerBasicStatisticsByUUIDJMSReceiveModel getPlayerDataByUUID(String uuid);

}
