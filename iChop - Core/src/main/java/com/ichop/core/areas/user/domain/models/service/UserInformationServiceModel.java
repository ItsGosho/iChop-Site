package com.ichop.core.areas.user.domain.models.service;

import com.ichop.core.base.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationServiceModel extends BaseServiceModel {

    private String statusMessage;
    private String avatarPath;
    private LocalDate birthDate;
    private String aboutYou;
    private UserServiceModel user;

}
