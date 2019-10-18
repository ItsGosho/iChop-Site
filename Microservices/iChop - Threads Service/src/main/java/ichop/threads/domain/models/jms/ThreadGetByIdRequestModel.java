package ichop.threads.domain.models.jms;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ThreadGetByIdRequestModel extends BaseRequestModel {

    @NotNull
    private String id;

}
