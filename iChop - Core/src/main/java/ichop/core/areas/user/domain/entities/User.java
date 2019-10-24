package ichop.core.areas.user.domain.entities;

import ichop.core.areas.log.domain.entities.UserLog;
import ichop.core.areas.role.domain.entities.UserRole;
import ichop.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document("users")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username", unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "is_account_non_expired", nullable = false)
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked", nullable = false)
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enable", nullable = false)
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = UserRole.class)
    private Set<UserRole> authorities;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Column(name = "last_online")
    private LocalDateTime lastOnline;

    @OneToMany(mappedBy = "user", targetEntity = UserLog.class)
    private List<UserLog> logs;

    @Column(name = "location")
    private String location;

    @OneToOne(mappedBy = "user", targetEntity = UserInformation.class)
    private UserInformation userInformation;

    public User() {
        this.setAuthorities(new HashSet<>());
    }
}
