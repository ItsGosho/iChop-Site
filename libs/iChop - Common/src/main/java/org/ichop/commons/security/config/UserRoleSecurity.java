package org.ichop.commons.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class UserRoleSecurity implements GrantedAuthority {

    private String id;
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}