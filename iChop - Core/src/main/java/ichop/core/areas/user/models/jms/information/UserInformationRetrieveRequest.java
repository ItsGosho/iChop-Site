package ichop.core.areas.user.models.jms.information;

import ichop.core.common.domain.BaseRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationRetrieveRequest extends BaseRequestModel {

    private String username;

}
