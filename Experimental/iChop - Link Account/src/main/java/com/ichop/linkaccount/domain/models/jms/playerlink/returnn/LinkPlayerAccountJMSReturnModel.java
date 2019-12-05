package com.ichop.linkaccount.domain.models.jms.playerlink.returnn;

import com.ichop.linkaccount.domain.models.jms.BaseJMSReturnModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkPlayerAccountJMSReturnModel extends BaseJMSReturnModel {

    private boolean isSuccessful;

}
