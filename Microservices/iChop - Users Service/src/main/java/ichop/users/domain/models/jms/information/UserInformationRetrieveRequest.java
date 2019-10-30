package ichop.users.domain.models.jms.information;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByEmail(#this.email) == true",message = "User not found!")
public class UserInformationRetrieveRequest extends BaseRequestModel {

    @NotNull
    private String email;

}