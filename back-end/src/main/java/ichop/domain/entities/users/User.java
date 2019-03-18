package ichop.domain.entities.users;

import ichop.domain.entities.BaseEntity;
import ichop.domain.entities.log.UserLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
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

    @Column(name = "last_online")
    private LocalDateTime lastOnline;

    @OneToMany(mappedBy = "user")
    private List<UserLog> logs;

    private String location;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_followers",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "follower_id",referencedColumnName = "id"))
    private Set<User> followings;

    @OneToOne(mappedBy = "user")
    private UserInformation userInformation;

    public User(){
        this.setAuthorities(new HashSet<>());
        this.setFollowings(new HashSet<>());
    }
}
