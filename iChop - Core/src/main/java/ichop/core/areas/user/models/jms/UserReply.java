package ichop.core.areas.user.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserReply extends ReplyCandidate implements UserDetails {

    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<RoleReply> authorities;
    private String authority;
    private LocalDateTime registrationDate;
    private LocalDateTime lastOnline;
    private String location;

}
