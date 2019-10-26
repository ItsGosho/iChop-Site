package ichop.users.domain.models.jms.retrieve;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.isUserExistsByEmail(#this.email) == true",message = "User not found!")
public class UserFindByEmailRequest extends BaseRequestModel {

    @NotNull
    private String email;

}
