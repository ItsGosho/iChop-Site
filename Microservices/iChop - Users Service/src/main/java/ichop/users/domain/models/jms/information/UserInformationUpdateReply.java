package ichop.users.domain.models.jms.information;

import ichop.users.common.domain.BaseReplyModel;
import ichop.users.common.domain.BaseRequestModel;
import ichop.users.common.validators.SpELValidation;
import ichop.users.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UserInformationUpdateReply extends BaseReplyModel {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;
    private UserServiceModel user;

}
