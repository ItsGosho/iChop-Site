package ichop.domain.models.view.user_options;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserProfileOptionsInformationViewModel {

    private String statusMessage;
    private String avatarPath;
    private LocalDate birthDate;
    private String aboutYou;

}
