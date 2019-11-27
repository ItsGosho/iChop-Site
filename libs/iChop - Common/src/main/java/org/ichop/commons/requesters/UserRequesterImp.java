package org.ichop.commons.requesters;

import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.domain.UserFindByEmailRequest;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserRequesterImp implements UserRequester {

    private final JmsHelper jmsHelper;

    private final String findByEmailDestination;

    @Autowired
    public UserRequesterImp(JmsHelper jmsHelper,
                            @Value("${artemis.queue.users.find.by.email}") String findByEmailDestination) {
        this.jmsHelper = jmsHelper;

        this.findByEmailDestination = findByEmailDestination;
    }

    @Override
    public JmsReplyModel findByEmail(String email) {
        UserFindByEmailRequest request = new UserFindByEmailRequest(email);

        return this.jmsHelper.sendAndReceive(this.findByEmailDestination, request);
    }
}
