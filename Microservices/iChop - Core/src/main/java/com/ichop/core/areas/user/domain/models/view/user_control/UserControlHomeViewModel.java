package com.ichop.core.areas.user.domain.models.view.user_control;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserControlHomeViewModel extends UserControlBaseViewModel {

    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private LocalDateTime registrationDate;
    private LocalDateTime lastOnline;
    private String location;



    public boolean getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }
}
