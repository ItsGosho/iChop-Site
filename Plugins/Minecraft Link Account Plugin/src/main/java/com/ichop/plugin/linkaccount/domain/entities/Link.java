package com.ichop.plugin.linkaccount.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "links")
public class Link extends BaseEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private String playerUUID;

    @Column(nullable = false, updatable = false)
    private String playerName;

    @Column(nullable = false)
    private String candidateUID;

    @Column(nullable = false)
    private LocalDateTime linkedOn;
}
