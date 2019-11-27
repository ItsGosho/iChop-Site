package ichop.users.domain.models.jms.information;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
public class UserInformationUpdateRequest extends RequestCandidate {

    @Length(max = 16)
    private String statusMessage;

    private String birthDate;

    @Length(max = 250)
    private String aboutYou;

    @NotNull
    private String username;

}
