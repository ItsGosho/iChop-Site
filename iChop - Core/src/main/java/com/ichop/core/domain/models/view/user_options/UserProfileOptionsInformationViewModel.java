package com.ichop.core.domain.models.view.user_options;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserProfileOptionsInformationViewModel {

    private String username;
    private String statusMessage;
    private String avatarPath;
    private LocalDate birthDate;
    private String aboutYou;

}
