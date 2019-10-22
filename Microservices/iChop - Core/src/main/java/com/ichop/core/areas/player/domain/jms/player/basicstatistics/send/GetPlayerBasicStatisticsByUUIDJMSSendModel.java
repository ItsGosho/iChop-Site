package com.ichop.core.areas.player.domain.jms.player.basicstatistics.send;

import com.ichop.core.base.BaseJMSSendModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerBasicStatisticsByUUIDJMSSendModel extends BaseJMSSendModel {

    private String uuid;

}
