package ichop.domain.entities.users;

import ichop.domain.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true,nullable = false,updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enable")
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> authorities;

    @Column(name = "registration_date",nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "last_online",nullable = false)
    private LocalDateTime lastOnline;

    public User(){
        this.setAuthorities(new HashSet<>());
    }

    public User(String username, String password, String email, Set<UserRole> authorities, LocalDateTime registrationDate) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.registrationDate = registrationDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
