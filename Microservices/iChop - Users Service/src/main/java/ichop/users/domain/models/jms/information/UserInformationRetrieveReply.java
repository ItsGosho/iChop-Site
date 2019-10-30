package ichop.users.domain.models.jms.information;

import ichop.users.common.domain.BaseReplyModel;
import ichop.users.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationRetrieveReply extends BaseReplyModel {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;
    private UserServiceModel user;

}
