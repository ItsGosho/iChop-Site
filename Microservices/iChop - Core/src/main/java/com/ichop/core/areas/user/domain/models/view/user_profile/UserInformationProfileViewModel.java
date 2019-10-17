package com.ichop.core.areas.user.domain.models.view.user_profile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInformationProfileViewModel {

    private String statusMessage;
    private LocalDate birthDate;
    private String aboutYou;

}
