package ichop.users.domain.models.service;

import ichop.users.common.domain.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationServiceModel extends BaseServiceModel {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;
    private UserServiceModel user;

}
