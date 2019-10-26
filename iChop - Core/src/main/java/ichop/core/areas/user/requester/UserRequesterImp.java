package ichop.core.areas.user.requester;

import ichop.core.areas.user.models.jms.register.UserRegisterReply;
import ichop.core.areas.user.models.jms.register.UserRegisterRequest;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailReply;
import ichop.core.areas.user.models.jms.retrieve.UserFindByEmailRequest;
import ichop.core.common.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserRequesterImp implements UserRequester {

    private final JmsHelper jmsHelper;

    private String findByEmailDestination;
    private String registerDestination;

    @Autowired
    public UserRequesterImp(JmsHelper jmsHelper,
                            @Value("${artemis.queue.users.find.by.email}") String findByEmailDestination,
                            @Value("${artemis.queue.users.authentication.register}") String registerDestination) {
        this.jmsHelper = jmsHelper;

        this.findByEmailDestination = findByEmailDestination;
        this.registerDestination = registerDestination;
    }

    @Override
    public UserFindByEmailReply findByEmail(String email) {
        UserFindByEmailRequest request = new UserFindByEmailRequest(email);

        return this.jmsHelper.sendAndReceive(this.findByEmailDestination, request, UserFindByEmailReply.class);
    }

    @Override
    public UserRegisterReply register(UserRegisterRequest request) {
        return this.jmsHelper.sendAndReceive(this.registerDestination, request, UserRegisterReply.class);
    }

}
