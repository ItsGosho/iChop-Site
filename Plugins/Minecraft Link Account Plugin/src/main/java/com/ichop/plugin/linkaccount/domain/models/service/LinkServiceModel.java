package com.ichop.plugin.linkaccount.domain.models.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkServiceModel extends BaseServiceModel {

    private String playerUUID;
    private String candidateUID;

}
