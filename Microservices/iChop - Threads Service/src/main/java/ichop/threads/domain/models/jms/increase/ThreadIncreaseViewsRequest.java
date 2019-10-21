package ichop.threads.domain.models.jms.increase;

import ichop.threads.common.domain.BaseRequestModel;
import ichop.threads.validators.ExistsById;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadIncreaseViewsRequest extends BaseRequestModel {

    @NotNull
    @ExistsById
    private String id;

}
