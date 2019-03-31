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
    private boolean isOnline;

    @Column(name = "DEATHS")
    private int totalDeaths;

    @Column(name = "PLAYER_KILLS")
    private int totalPlayerKills;

    @Column(name = "MOB_KILLS")
    private int totalMobKills;

    @Column(name = "DAMAGE_DEALT")
    private int totalDamageDealt;

    @Column(name = "DAMAGE_TAKEN")
    private int totalDamageTaken;

    @Column(name = "LAST_JOIN")
    private int lastJoin;
}
