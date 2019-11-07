package ichop.users.domain.models.jms;

import lombok.Getter;
import lombok.Setter;
import org.ichop.commons.domain.ReplyCandidate;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class RoleReply extends ReplyCandidate implements GrantedAuthority {

    private String id;
    private String authority;

}
