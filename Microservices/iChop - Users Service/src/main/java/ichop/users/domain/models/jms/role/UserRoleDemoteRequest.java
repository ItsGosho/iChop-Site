package ichop.users.domain.models.jms.role;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
@SpELValidation(value = "@userServicesImp.hasPreviousRole(#this.username) == true",message = "User doesn't have previous role!")
public class UserRoleDemoteRequest extends RequestCandidate {

    private String username;

}
