package com.ichop.plugin.linkaccount.domain.models.service;

import com.ichop.plugin.linkaccount.commons.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LinkServiceModel extends BaseServiceModel {

    private String playerUUID;
    private String playerName;
    private String candidateUID;
    private LocalDateTime linkedOn;

}
