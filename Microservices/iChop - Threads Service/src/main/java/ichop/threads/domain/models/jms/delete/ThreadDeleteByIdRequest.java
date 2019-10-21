package ichop.threads.domain.models.jms.delete;

import ichop.threads.common.domain.BaseRequestModel;
import ichop.threads.validators.ExistsById;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadDeleteByIdRequest extends BaseRequestModel {

    @NotNull
    @ExistsById
    private String id;

}
