package ichop.threads.domain.models.jms.increase;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseRequestModel;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadIncreaseViewsRequest extends BaseRequestModel {

    @NotNull
    private String id;

}
