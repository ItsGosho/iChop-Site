package com.ichop.linkaccount.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerLinkCreateBindingModel {

    private String playerUUID;
    private String playerName;
    private String siteUserUsername;

}
