package com.ichop.linkaccount.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "players_accounts_links")
public class PlayerLink {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(name = "player_uuid",nullable = false, unique = true, updatable = false)
    private String playerUUID;

    @Column(name = "player_name",nullable = false)
    private String playerName;

    @Column(name = "site_user_username",unique = true,nullable = false,updatable = false)
    private String siteUserUsername;
}
