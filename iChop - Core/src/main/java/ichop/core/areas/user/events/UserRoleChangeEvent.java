package ichop.core.areas.user.events;

import ichop.core.areas.user.domain.entities.User;
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
