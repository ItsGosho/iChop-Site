package ichop.link.requesters;

import org.ichop.commons.domain.JmsReplyModel;

public interface KeyRequester {

    JmsReplyModel isKeyValid(String key);
    JmsReplyModel findKeyByKey(String key);

}
