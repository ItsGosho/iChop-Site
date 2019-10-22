package com.ichop.core.areas.role.domain.entities;

import com.ichop.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "UserRole")
@Table(name = "users_roles")
public class UserRole extends BaseEntity implements GrantedAuthority {

    @Column(unique = true)
    private String authority;

}
