package com.ichop.core.events;

import com.ichop.core.domain.entities.users.User;
import org.springframework.context.ApplicationEvent;

public class UserRoleChangeEvent extends ApplicationEvent {

    private User user;

    public UserRoleChangeEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
