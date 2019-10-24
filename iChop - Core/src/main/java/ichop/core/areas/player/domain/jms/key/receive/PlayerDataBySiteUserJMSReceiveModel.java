package ichop.core.areas.player.domain.jms.key.receive;

import ichop.core.base.BaseJMSReceiveModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDataBySiteUserJMSReceiveModel extends BaseJMSReceiveModel {

    private String playerUUID;
    private String playerName;
}
