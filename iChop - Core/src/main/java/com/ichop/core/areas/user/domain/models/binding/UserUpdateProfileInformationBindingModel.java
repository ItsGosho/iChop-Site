package com.ichop.core.areas.user.domain.models.binding;

import com.ichop.core.areas.user.domain.models.service.UserServiceModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateProfileInformationBindingModel {


    @Length(max = 16)
    private String statusMessage;

    private String avatarBinary;

    private String birthDate;

    @Length(max = 250)
    private String aboutYou;

    @NotNull
    private UserServiceModel user;

}
