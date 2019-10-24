package ichop.core.areas.player.domain.jms.player.basicstatistics.send;

import ichop.core.base.BaseJMSSendModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayerBasicStatisticsByUUIDJMSSendModel extends BaseJMSSendModel {

    private String uuid;

}
