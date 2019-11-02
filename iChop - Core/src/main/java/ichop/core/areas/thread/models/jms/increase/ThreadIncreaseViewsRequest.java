package ichop.core.areas.thread.models.jms.increase;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadIncreaseViewsRequest extends BaseRequestModel {

    private String id;

}
