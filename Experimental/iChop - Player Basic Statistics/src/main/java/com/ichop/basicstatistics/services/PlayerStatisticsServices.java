package com.ichop.basicstatistics.services;

import com.ichop.basicstatistics.domain.models.service.PlayerStatisticsServiceModel;

public interface PlayerStatisticsServices {

    PlayerStatisticsServiceModel findByUUID(String uuid);

    boolean existsByUUID(String uuid);

}
