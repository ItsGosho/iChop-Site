package ichop.link.requesters;

import org.ichop.commons.domain.JmsReplyModel;

public interface LinkRequester {

    JmsReplyModel linkCreate(String key,String username);

    JmsReplyModel linkRemove(String username);

    JmsReplyModel linkRetrieve(String username);

}
