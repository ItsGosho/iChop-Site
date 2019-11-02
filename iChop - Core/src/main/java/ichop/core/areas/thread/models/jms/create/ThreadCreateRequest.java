package ichop.core.areas.thread.models.jms.create;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadCreateRequest extends BaseRequestModel {


    private String title;
    private String content;
    private String userId;

}
