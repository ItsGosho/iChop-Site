package com.ichop.minecraft.linkaccount.domain.models.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerLinkServiceModel extends BaseServiceModel {

    private String playerUUID;
    private String siteUserUsername;

}
