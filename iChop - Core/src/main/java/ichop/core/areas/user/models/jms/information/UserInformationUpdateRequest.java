package ichop.core.areas.user.models.jms.information;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.RequestCandidate;

@Getter
@Setter
public class UserInformationUpdateRequest extends RequestCandidate {

    private String statusMessage;
    private String avatarBinary;
    private String birthDate;
    private String aboutYou;
    private String username;

}
