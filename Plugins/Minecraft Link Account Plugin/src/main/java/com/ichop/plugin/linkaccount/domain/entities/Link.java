package com.ichop.plugin.linkaccount.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "links")
public class Link extends BaseEntity {

    @Column(name = "[player_uuid]", unique = true, nullable = false, updatable = false)
    private String playerUUID;

    @Column(name = "[candidate_uid]", nullable = false)
    private String candidateUID;

}
