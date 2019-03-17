package ichop.domain.entities.users;

import ichop.domain.entities.BaseEntity;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Entity
@Table(name = "users_roles")
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

}
