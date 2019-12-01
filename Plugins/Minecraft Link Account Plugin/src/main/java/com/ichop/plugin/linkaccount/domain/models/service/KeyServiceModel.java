package com.ichop.plugin.linkaccount.domain.models.service;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class KeyServiceModel extends BaseServiceModel {

    private String linkKey;
    private String playerUUID;
    private String playerName;


    private LocalDateTime expirationDate;
}
