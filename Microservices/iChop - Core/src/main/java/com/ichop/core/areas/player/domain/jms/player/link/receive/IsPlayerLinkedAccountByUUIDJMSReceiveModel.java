package com.ichop.core.areas.player.domain.jms.player.link.receive;

import com.ichop.core.base.BaseJMSReceiveModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsPlayerLinkedAccountByUUIDJMSReceiveModel extends BaseJMSReceiveModel {

    private boolean isLinked;

}
