package ichop.events;

import ichop.domain.entities.users.User;
import ichop.service.log.UserLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserRoleChangeEventListener {

    private final UserLogServices userLogServices;

    @Autowired
    public UserRoleChangeEventListener(UserLogServices userLogServices) {
        this.userLogServices = userLogServices;
    }

    @Async
    @EventListener
    public void roleChangeEvent(UserRoleChangeEvent userRoleChangeEvent){
        User user = userRoleChangeEvent.getUser();
        System.out.println("Here :))");

    }

}
