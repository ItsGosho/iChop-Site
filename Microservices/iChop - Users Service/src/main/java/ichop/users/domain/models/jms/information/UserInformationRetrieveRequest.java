package ichop.users.domain.models.jms.information;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsById(#this.userId) == true",message = "User not found!")
public class UserInformationRetrieveRequest extends BaseRequestModel {

    @NotNull
    private String userId;

}
