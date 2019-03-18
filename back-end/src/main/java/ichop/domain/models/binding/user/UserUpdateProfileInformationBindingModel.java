package ichop.domain.models.binding.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateProfileInformationBindingModel {


    @Length(max = 16)
    private String statusMessage;

    private String avatarPath;

    private LocalDate birthDate;

    @Length(max = 250)
    private String aboutYou;

}
