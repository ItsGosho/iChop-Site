package ichop.link.requesters;

import ichop.link.domain.models.jms.LinkCreateJmsRequest;
import ichop.link.domain.models.jms.LinkRemoveJmsRequest;
import ichop.link.domain.models.jms.LinkRetrieveJmsRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LinkRequesterImpl implements LinkRequester {

    private final JmsHelper jmsHelper;

    private final String linkCreateDestination;
    private final String linkRemoveDestination;
    private final String linkRetrieveDestination;

    @Autowired
    public LinkRequesterImpl(JmsHelper jmsHelper,
                             @Value("${artemis.queue.link_account.link.create}") String linkCreateDestination,
                             @Value("${artemis.queue.link_account.link.remove}") String linkRemoveDestination,
                             @Value("${artemis.queue.link_account.link.retrieve}") String linkRetrieveDestination) {
        this.jmsHelper = jmsHelper;

        this.linkCreateDestination = linkCreateDestination;
        this.linkRemoveDestination = linkRemoveDestination;
        this.linkRetrieveDestination = linkRetrieveDestination;
    }


    @Override
    public JmsReplyModel linkCreate(String key, String username) {
        LinkCreateJmsRequest request = new LinkCreateJmsRequest(key, username);

        return this.jmsHelper.sendAndReceive(this.linkCreateDestination, request);
    }

    @Override
    public JmsReplyModel linkRemove(String username) {
        LinkRemoveJmsRequest request = new LinkRemoveJmsRequest(username);

        return this.jmsHelper.sendAndReceive(this.linkRemoveDestination, request);
    }

    @Override
    public JmsReplyModel linkRetrieve(String username) {
        LinkRetrieveJmsRequest request = new LinkRetrieveJmsRequest(username);

        return this.jmsHelper.sendAndReceive(this.linkRetrieveDestination, request);
    }
}
