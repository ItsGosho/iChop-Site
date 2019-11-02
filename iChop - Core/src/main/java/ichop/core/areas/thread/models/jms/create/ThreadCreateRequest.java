package ichop.core.areas.thread.models.jms.create;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseRequestModel;

@Getter
@Setter
public class ThreadCreateRequest extends BaseRequestModel {


    private String title;
    private String content;
    private String creatorUsername;

}
