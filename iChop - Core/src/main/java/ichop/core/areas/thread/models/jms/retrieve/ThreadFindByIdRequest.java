package ichop.core.areas.thread.models.jms.retrieve;


import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadFindByIdRequest extends BaseRequestModel {

    private String id;

}
