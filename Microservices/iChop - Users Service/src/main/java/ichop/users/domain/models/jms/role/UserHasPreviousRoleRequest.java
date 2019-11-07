package ichop.users.domain.models.jms.role;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
public class UserHasPreviousRoleRequest extends RequestCandidate {

    private String username;

}
