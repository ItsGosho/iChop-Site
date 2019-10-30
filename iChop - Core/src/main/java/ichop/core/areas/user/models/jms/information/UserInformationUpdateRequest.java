package ichop.core.areas.user.models.jms.information;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInformationUpdateRequest extends BaseRequestModel {

    private String statusMessage;
    private String avatarBinary;
    private String birthDate;
    private String aboutYou;
    private String username;

}
