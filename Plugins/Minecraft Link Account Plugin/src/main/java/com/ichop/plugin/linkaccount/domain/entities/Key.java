package com.ichop.plugin.linkaccount.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "keys")
public class Key extends BaseEntity {

    @Column(name = "[key]",unique = true, nullable = false, updatable = false)
    private String key;

    @Column(name = "[player_uuid]",unique = true, nullable = false, updatable = false)
    private String playerUUID;

    @Column(name = "[player_name]",nullable = false)
    private String playerName;

    @Column(name = "[expiration_date]",nullable = false, updatable = false)
    private LocalDateTime expirationDate;

}
