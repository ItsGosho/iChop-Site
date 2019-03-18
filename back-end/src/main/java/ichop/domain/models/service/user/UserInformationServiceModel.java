package ichop.domain.models.service.user;

import ichop.domain.models.service.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationServiceModel extends BaseServiceModel {

    private String statusMessage;
    private String avatarPath;
    private LocalDate birthDate;
    private String aboutYou;
    private UserServiceModel user;

}
