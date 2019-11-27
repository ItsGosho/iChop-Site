package ichop.comments.requesters;

import org.ichop.commons.domain.JmsReplyModel;

public interface ThreadRequester {

    JmsReplyModel findById(String id);


}
