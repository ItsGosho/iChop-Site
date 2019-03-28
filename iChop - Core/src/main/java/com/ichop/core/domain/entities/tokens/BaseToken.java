package com.ichop.core.domain.entities.tokens;

import com.ichop.core.domain.entities.BaseEntity;
import com.ichop.core.domain.entities.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseToken extends BaseEntity {

    @Column(name = "token",nullable = false,updatable = false)
    private String token;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(name = "expiry_date",nullable = false,updatable = false)
    private LocalDateTime expiryDate;
}
