package com.ichop.core.areas.player.domain.jms.player.link.send;

import com.ichop.core.base.BaseJMSSendModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerDataByPlayerUUIDJMSSendModel extends BaseJMSSendModel {

    private String uuid;

}
