package ichop.users.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.MySQLEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "Role")
@Table(name = "roles")
public class Role extends MySQLEntity implements GrantedAuthority {

    @Column(unique = true)
    private String authority;

}
