package com.ichop.core.areas.user.domain.models.binding;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserUpdateProfileInformationBindingModel {


    @Length(max = 16)
    private String statusMessage;

    private String avatarBinary;

    private String birthDate;

    @Length(max = 250)
    private String aboutYou;

}
