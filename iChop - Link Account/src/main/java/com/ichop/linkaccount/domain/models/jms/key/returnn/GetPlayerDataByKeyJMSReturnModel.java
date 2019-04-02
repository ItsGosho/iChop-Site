package com.ichop.linkaccount.domain.models.jms.key.returnn;

import com.ichop.linkaccount.domain.models.jms.BaseJMSReturnModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerDataByKeyJMSReturnModel extends BaseJMSReturnModel {

    private String playerUUID;
    private String playerName;

}
