package com.ichop.core.areas.player.domain.jms.player.basicstatistics.receive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ichop.core.base.BaseJMSReceiveModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetPlayerBasicStatisticsByUUIDJMSReceiveModel extends BaseJMSReceiveModel {

    private String uuid;
    private String name;
    private boolean isOnline;
    private int totalDeaths;
    private int totalPlayerKills;
    private int totalMobKills;
    private int totalDamageDealt;
    private int totalDamageTaken;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastJoin;

}
