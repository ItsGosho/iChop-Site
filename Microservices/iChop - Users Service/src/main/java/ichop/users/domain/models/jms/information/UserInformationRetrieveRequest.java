package ichop.users.domain.models.jms.information;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
public class UserInformationRetrieveRequest extends RequestCandidate {

    @NotNull
    private String username;

}
