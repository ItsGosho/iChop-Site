package ichop.core.areas.player.domain.jms.player.link.send;

import ichop.core.base.BaseJMSSendModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnlinkPlayerAccountJMSSendModel extends BaseJMSSendModel {

    private String siteUserUsername;

}
