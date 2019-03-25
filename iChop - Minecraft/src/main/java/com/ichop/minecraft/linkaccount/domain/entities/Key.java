package com.ichop.minecraft.linkaccount.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "players_accounts_links_keys")
@Immutable
public class Key {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "key")
    private String key;

    @Column(name = "player_uuid")
    private String playerUUID;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "expirity_date")
    private LocalDateTime expirityDate;

}