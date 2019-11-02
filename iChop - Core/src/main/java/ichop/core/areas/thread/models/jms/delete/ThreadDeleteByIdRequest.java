package ichop.core.areas.thread.models.jms.delete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.BaseRequestModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadDeleteByIdRequest extends BaseRequestModel {

    private String id;

}
