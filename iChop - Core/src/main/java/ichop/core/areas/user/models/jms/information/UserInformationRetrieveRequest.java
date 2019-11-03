package ichop.core.areas.user.models.jms.information;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationRetrieveRequest extends RequestCandidate {

    private String username;

}
