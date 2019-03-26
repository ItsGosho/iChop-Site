package com.ichop.core.domain.models.jms.player.link.send;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkPlayerAccountJMSSendModel {

    private String playerName;
    private String playerUUID;
    private String siteUserUsername;

}
