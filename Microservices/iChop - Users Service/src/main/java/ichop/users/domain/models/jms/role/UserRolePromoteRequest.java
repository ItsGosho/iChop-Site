package ichop.users.domain.models.jms.role;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;
import org.ichop.commons.validators.SpELValidation;

@Getter
@Setter
@SpELValidation(value = "@userServicesImp.existsByUsername(#this.username) == true",message = "User not found!")
@SpELValidation(value = "@userServicesImp.hasNextRole(#this.username) == true",message = "User doesn't have next role!")
public class UserRolePromoteRequest extends RequestCandidate {

    private String username;

}
