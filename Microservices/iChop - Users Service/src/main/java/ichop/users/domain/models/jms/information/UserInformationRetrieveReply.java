package ichop.users.domain.models.jms.information;

import ichop.users.common.domain.BaseReplyModel;
import ichop.users.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInformationRetrieveReply extends BaseReplyModel {

    private String statusMessage;
    private String birthDate;
    private String aboutYou;
    private UserServiceModel user;

}
