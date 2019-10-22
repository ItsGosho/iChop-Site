package com.ichop.core.areas.user.domain.models.service;

import com.ichop.core.areas.role.domain.models.service.UserRoleServiceModel;
import com.ichop.core.base.BaseServiceModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserServiceModel extends BaseServiceModel implements Serializable {

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
    private UserInformationServiceModel userInformation;


}
