package com.ichop.core.areas.log.domain.entities;

import com.ichop.core.areas.user.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "UserLog")
@Table(name = "users_logs")
public class UserLog extends BaseLog {

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_type",nullable = false,updatable = false)
    private UserLogType logType;

}
