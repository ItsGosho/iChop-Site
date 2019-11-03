package ichop.users.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.BaseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean isAccountNonExpired;

    @Column(nullable = false)
    private boolean isAccountNonLocked;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired;

    @Column(nullable = false)
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Role.class)
    private Set<Role> authorities;

    private LocalDateTime registrationDate = LocalDateTime.now();

    private LocalDateTime lastOnline = LocalDateTime.now();

    private String location;

    public User() {
        this.setAuthorities(new HashSet<>());
    }
}
