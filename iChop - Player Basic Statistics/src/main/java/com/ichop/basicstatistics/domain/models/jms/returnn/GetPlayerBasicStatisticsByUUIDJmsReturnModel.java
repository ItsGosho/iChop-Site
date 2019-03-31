package com.ichop.basicstatistics.domain.models.jms.returnn;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class GetPlayerBasicStatisticsByUUIDJmsReturnModel extends BaseReturnModel {

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
