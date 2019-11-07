package ichop.users.domain.models.service;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseServiceModel;

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
    private Set<RoleServiceModel> authorities;
    private LocalDateTime registrationDate;
    private LocalDateTime lastOnline;
    private String location;

}
