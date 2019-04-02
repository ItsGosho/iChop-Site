package com.ichop.linkaccount.domain.models.jms.playerlink.returnn;

import com.ichop.linkaccount.domain.models.jms.BaseJMSReturnModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerDataByPlayerUUIDJMSReturnModel extends BaseJMSReturnModel {

    private String playerUUID;
    private String playerName;
    private String siteUserUsername;

}
