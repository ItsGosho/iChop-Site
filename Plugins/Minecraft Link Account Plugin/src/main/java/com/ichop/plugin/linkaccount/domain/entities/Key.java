package com.ichop.plugin.linkaccount.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "link_keys")
public class Key extends BaseEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private String linkKey;

    @Column(unique = true, nullable = false, updatable = false)
    private String playerUUID;

    @Column(nullable = false)
    private String playerName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime expirationDate;

}
