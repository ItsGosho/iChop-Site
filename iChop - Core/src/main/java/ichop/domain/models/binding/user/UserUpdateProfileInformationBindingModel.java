package ichop.domain.models.binding.user;

import ichop.validators.Base64;
import ichop.validators.Base64Image;
import ichop.validators.Base64Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserUpdateProfileInformationBindingModel {


    @Length(max = 16)
    private String statusMessage;

    @Base64
    @Base64Size(maxInMB = 1.00)
    @Base64Image(maxHeight = 200,maxWidth = 200)
    private String avatarBinary;

    private String birthDate;

    @Length(max = 250)
    private String aboutYou;

}
