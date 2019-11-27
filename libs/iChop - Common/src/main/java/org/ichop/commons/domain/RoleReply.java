package org.ichop.commons.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class RoleReply extends ReplyCandidate implements GrantedAuthority {

    private String id;
    private String authority;

}
