package ichop.threads.domain.models.jms.delete;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseRequestModel;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@threadServicesImp.existsById(#this.id) == true",message = "Thread not found!")
public class ThreadDeleteByIdRequest extends BaseRequestModel {

    @NotNull
    private String id;

}
