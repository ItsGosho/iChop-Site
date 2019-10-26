package ichop.core.areas.user.models.jms.register;

import ichop.core.areas.security.config.UserRoleSecurity;
import ichop.core.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserRegisterReply extends BaseReplyModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<UserRoleSecurity> authorities;
    private LocalDateTime registrationDate;
    private LocalDateTime lastOnline;
    private String location;

}
