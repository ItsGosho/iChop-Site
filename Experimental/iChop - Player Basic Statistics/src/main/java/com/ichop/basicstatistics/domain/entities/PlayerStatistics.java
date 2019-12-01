package com.ichop.basicstatistics.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@Entity
@Table(name = "stats")
@Immutable
public class PlayerStatistics {

    @Id
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "is_online")
    private Boolean isOnline;

    @Column(name = "DEATHS")
    private Integer totalDeaths;

    @Column(name = "PLAYER_KILLS")
    private Integer totalPlayerKills;

    @Column(name = "MOB_KILLS")
    private Integer totalMobKills;

    @Column(name = "DAMAGE_DEALT")
    private Integer totalDamageDealt;

    @Column(name = "DAMAGE_TAKEN")
    private Integer totalDamageTaken;

    @Column(name = "LAST_JOIN")
    private Integer lastJoin;
}
