package com.ichop.core.domain.entities.log;

import com.ichop.core.domain.entities.users.User;
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
