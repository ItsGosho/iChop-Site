package ichop.users.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationServiceModel extends BaseServiceModel {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;
    private UserServiceModel user;

}
