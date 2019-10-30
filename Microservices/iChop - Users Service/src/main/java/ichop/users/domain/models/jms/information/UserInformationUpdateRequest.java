package ichop.users.domain.models.jms.information;

import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import ichop.users.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsById(#this.userId) == true",message = "User not found!")
public class UserInformationUpdateRequest extends BaseRequestModel {

    @Length(max = 16)
    private String statusMessage;

    private String avatarBinary;

    private String birthDate;

    @Length(max = 250)
    private String aboutYou;

    @NotNull
    private String userId;

}
