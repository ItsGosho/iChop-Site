package ichop.core.areas.reaction.requesters;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.reaction.models.ReactionOn;
import ichop.core.areas.reaction.models.jms.check.ReactionIsReactedRequest;
import ichop.core.areas.reaction.models.jms.create.ReactionCreateRequest;
import ichop.core.areas.reaction.models.jms.find.ReactionsFindByRequest;
import org.ichop.commons.domain.JmsReplyModel;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReactionRequesterImp implements ReactionRequester {

    private final JmsHelper jmsHelper;
    private final ObjectMapper objectMapper;

    private final String createDestination;
    private final String isReactedDestination;
    private final String findByDestination;

    @Autowired
    public ReactionRequesterImp(JmsHelper jmsHelper,
                                ObjectMapper objectMapper,
                                @Value("${artemis.queue.reactions.create}") String createDestination,
                                @Value("${artemis.queue.reactions.is.reacted}") String isReactedDestination,
                                @Value("${artemis.queue.reactions.find.by}") String findByDestination) {

        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;

        this.createDestination = createDestination;
        this.isReactedDestination = isReactedDestination;
        this.findByDestination = findByDestination;
    }

    @Override
    public JmsReplyModel create(ReactionCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request);
    }

    @Override
    public JmsReplyModel isReacted(String creatorUsername, String entityId, ReactionOn reactionOn) {
        ReactionIsReactedRequest request = new ReactionIsReactedRequest(creatorUsername, entityId, reactionOn);

        return this.jmsHelper.sendAndReceive(this.isReactedDestination, request);
    }

    @Override
    public JmsReplyModel findBy(ReactionsFindByRequest request) {
        return this.jmsHelper.sendAndReceive(this.findByDestination,request);
    }

}
