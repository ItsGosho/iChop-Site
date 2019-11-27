package ichop.core.areas.user.models.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationViewModel {

    private String id;
    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;

}
