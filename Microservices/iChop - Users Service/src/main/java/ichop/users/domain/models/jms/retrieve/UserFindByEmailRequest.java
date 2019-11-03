package ichop.users.domain.models.jms.retrieve;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByEmail(#this.email) == true",message = "User not found!")
public class UserFindByEmailRequest extends RequestCandidate {

    @NotNull
    private String email;

}
