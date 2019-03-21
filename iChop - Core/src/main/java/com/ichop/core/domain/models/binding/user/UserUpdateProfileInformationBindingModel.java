package com.ichop.core.domain.models.binding.user;

import com.ichop.core.validators.Base64;
import com.ichop.core.validators.Base64Image;
import com.ichop.core.validators.Base64Size;
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
