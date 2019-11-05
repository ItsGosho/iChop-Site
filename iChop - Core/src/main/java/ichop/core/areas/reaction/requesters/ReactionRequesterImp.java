package ichop.core.areas.reaction.requesters;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.core.areas.reaction.models.ReactionOn;
import ichop.core.areas.reaction.models.jms.check.ReactionIsReactedRequest;
import ichop.core.areas.reaction.models.jms.create.ReactionCreateRequest;
import org.ichop.commons.domain.BoolReply;
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

    @Autowired
    public ReactionRequesterImp(JmsHelper jmsHelper,
                                ObjectMapper objectMapper,
                                @Value("${artemis.queue.reactions.create}") String createDestination,
                                @Value("${artemis.queue.reactions.is.reacted}") String isReactedDestination) {

        this.jmsHelper = jmsHelper;
        this.objectMapper = objectMapper;

        this.createDestination = createDestination;
        this.isReactedDestination = isReactedDestination;
    }

    @Override
    public JmsReplyModel create(ReactionCreateRequest request) {
        return this.jmsHelper.sendAndReceive(this.createDestination, request);
    }

    @Override
    public boolean isReacted(String userId, String entityId, ReactionOn reactionOn) {
        ReactionIsReactedRequest request = new ReactionIsReactedRequest(userId, entityId, reactionOn);

        JmsReplyModel reply = this.jmsHelper.sendAndReceive(this.isReactedDestination, request);

        return reply.isSuccessful() ? this.objectMapper.convertValue(reply, BoolReply.class).getResult() : false;
    }

}
