package ichop.threads.domain.models.jms.delete;

import ichop.threads.common.domain.BaseRequestModel;
import ichop.threads.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@threadServicesImp.existsById(#this.id) == true",message = "Thread not found!")
public class ThreadDeleteByIdRequest extends BaseRequestModel {

    @NotNull
    private String id;

}
