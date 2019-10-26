package ichop.user.areas.user.domain.entities;

import ichop.user.areas.role.domain.entities.UserRole;
import ichop.user.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document("users")
public class User extends BaseEntity implements UserDetails {

    @NotNull
    @Indexed(unique = true)
    private String username;

    @NotNull
    private String password;

    @Indexed(unique = true)
    private String email;

    @NotNull
    private boolean isAccountNonExpired;

    @NotNull
    private boolean isAccountNonLocked;

    @NotNull
    private boolean isCredentialsNonExpired;

    @NotNull
    private boolean isEnabled;

    @DBRef
    private Set<UserRole> authorities;

    private LocalDateTime registrationDate = LocalDateTime.now();

    private LocalDateTime lastOnline = LocalDateTime.now();

    private String location;

    public User() {
        this.setAuthorities(new HashSet<>());
    }
}
