package ichop.core.areas.thread.models.jms.delete;

import ichop.core.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadDeleteByIdRequest extends BaseRequestModel {

    private String id;

}
