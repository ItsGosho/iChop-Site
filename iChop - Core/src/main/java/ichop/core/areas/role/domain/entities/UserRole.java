package ichop.core.areas.role.domain.entities;

import ichop.core.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Document("roles")
public class UserRole extends BaseEntity implements GrantedAuthority {

    @Indexed(unique = true)
    private String authority;

}
