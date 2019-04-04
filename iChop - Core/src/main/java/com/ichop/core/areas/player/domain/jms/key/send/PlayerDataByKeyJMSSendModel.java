package com.ichop.core.areas.player.domain.jms.key.send;

import com.ichop.core.base.BaseJMSSendModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDataByKeyJMSSendModel extends BaseJMSSendModel {

    private String key;
}
