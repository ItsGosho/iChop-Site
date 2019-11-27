package org.ichop.commons.requesters;

import org.ichop.commons.domain.JmsReplyModel;

public interface UserRequester {

    JmsReplyModel findByEmail(String email);

    JmsReplyModel findByUsername(String username);
}
