package com.ichop.plugin.linkaccount.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "players_accounts_links_keys")
public class Key {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "[id]", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name = "[key]",unique = true, nullable = false, updatable = false)
    private String key;

    @Column(name = "[player_uuid]",unique = true, nullable = false, updatable = false)
    private String playerUUID;

    @Column(name = "[player_name]",nullable = false)
    private String playerName;

    @Column(name = "[expirity_date]",nullable = false, updatable = false)
    private LocalDateTime expirityDate;

}
