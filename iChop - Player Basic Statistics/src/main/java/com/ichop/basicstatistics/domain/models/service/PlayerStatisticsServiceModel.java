package com.ichop.basicstatistics.domain.models.service;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlayerStatisticsServiceModel extends BaseServiceModel {

    private Long id;
    private String uuid;
    private String name;
    private boolean isOnline;
    private int totalDeaths;
    private int totalPlayerKills;
    private int totalMobKills;
    private int totalDamageDealt;
    private int totalDamageTaken;
    private LocalDateTime lastJoin;

}
