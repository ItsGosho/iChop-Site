package com.ichop.linkaccount.domain.models.jms.link.receive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkPlayerAccountJMSReceiveModel {

    private String playerName;
    private String playerUUID;
    private String siteUserUsername;

}
