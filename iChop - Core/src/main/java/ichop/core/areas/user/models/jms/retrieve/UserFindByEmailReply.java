package ichop.core.areas.user.models.jms.retrieve;

import ichop.core.common.domain.BaseReplyModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserFindByEmailReply extends BaseReplyModel implements UserDetails {

    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<? extends GrantedAuthority> authorities;
    private LocalDateTime registrationDate;
    private LocalDateTime lastOnline;
    private String location;

}
