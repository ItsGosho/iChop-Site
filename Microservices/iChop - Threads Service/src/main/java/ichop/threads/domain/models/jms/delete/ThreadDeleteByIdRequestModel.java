package ichop.threads.domain.models.jms.delete;

import ichop.threads.domain.models.jms.BaseRequestModel;
import ichop.threads.validators.ExistsById;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadDeleteByIdRequestModel extends BaseRequestModel {

    @NotNull
    @ExistsById
    private String id;

}
