package com.ichop.linkaccount.domain.models.jms.key.returnn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerDataBySiteUserJMSReturnModel {

    private String playerUUID;
    private String playerName;
}
