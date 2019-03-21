package com.ichop.core.domain.models.service.user;

import com.ichop.core.domain.models.service.BaseServiceModel;
import com.ichop.core.domain.models.service.role.UserRoleServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<UserRoleServiceModel> authorities;
    private LocalDateTime registrationDate;
    private LocalDateTime lastOnline;
    private String location;
    private Set<UserServiceModel> followings;
    private UserInformationServiceModel userInformation;


}
