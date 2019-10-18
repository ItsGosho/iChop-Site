package ichop.threads.domain.models.jms;


import ichop.threads.validators.ExistsById;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadGetByIdRequestModel extends BaseRequestModel {

    @NotNull
    @ExistsById
    private String id;

}
