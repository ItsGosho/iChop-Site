package ichop.core.areas.user.models.jms.information;

import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailReply;
import ichop.core.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationUpdateReply extends BaseReplyModel {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;
    private UserFindByEmailReply user;

}
