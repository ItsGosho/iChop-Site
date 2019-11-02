package ichop.core.areas.thread.models.jms.increase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.BaseRequestModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreadIncreaseViewsRequest extends BaseRequestModel {

    private String id;

}
