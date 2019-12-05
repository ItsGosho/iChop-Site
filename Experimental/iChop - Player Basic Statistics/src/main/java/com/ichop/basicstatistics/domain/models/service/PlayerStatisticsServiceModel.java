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
    private Boolean isOnline;
    private Integer totalDeaths;
    private Integer totalPlayerKills;
    private Integer totalMobKills;
    private Integer totalDamageDealt;
    private Integer totalDamageTaken;
    private LocalDateTime lastJoin;

}
