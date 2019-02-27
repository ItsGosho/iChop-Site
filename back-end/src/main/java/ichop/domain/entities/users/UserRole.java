package ichop.domain.entities.users;

import ichop.domain.entities.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole extends BaseEntity implements GrantedAuthority {

    @Column(unique = true)
    private String authority;

    public UserRole(){

    }

    public UserRole(String authority) {
        this();
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
