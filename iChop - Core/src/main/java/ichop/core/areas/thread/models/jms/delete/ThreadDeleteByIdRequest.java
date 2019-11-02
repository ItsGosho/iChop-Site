package ichop.core.areas.thread.models.jms.delete;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadDeleteByIdRequest extends BaseRequestModel {

    private String id;

}
