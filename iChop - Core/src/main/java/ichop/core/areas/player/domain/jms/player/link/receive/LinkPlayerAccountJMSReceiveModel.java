package ichop.core.areas.player.domain.jms.player.link.receive;

import ichop.core.base.BaseJMSReceiveModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkPlayerAccountJMSReceiveModel extends BaseJMSReceiveModel {

    private boolean isSuccessful;

}
