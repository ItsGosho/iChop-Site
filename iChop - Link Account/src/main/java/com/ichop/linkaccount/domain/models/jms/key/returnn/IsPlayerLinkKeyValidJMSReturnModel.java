package com.ichop.linkaccount.domain.models.jms.key.returnn;

import com.ichop.linkaccount.domain.models.jms.BaseJMSReturnModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsPlayerLinkKeyValidJMSReturnModel extends BaseJMSReturnModel {

    private boolean isValid;
}
