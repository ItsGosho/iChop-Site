package ichop.threads.domain.models.jms.retrieve;


import ichop.threads.common.domain.BaseRequestModel;
import ichop.threads.validators.ExistsBy;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadGetByIdRequest extends BaseRequestModel {

    @NotNull
    @ExistsBy(field = "_id",message = "Thread doesn't exists with that id!")
    private String id;

}
