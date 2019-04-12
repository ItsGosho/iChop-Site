package com.ichop.basicstatistics.domain.models.jms.returnn;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetPlayerBasicStatisticsByUUIDJmsReturnModel extends BaseJMSReturnModel {

    private String uuid;
    private String name;
    private Boolean isOnline;
    private Integer totalDeaths;
    private Integer totalPlayerKills;
    private Integer totalMobKills;
    private Integer totalDamageDealt;
    private Integer totalDamageTaken;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastJoin;

}
