package ichop.core.areas.player.domain.jms.key.send;

import ichop.core.base.BaseJMSSendModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDataBySiteUserJMSSendModel extends BaseJMSSendModel {

    private String siteUserUsername;
}
