package com.ichop.core.domain.models.binding.user;

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
