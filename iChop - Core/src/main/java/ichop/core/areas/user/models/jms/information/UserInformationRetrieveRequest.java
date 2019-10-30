package ichop.core.areas.user.models.jms.information;

import ichop.core.common.domain.BaseRequestModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInformationRetrieveRequest extends BaseRequestModel {

    private String username;

}
