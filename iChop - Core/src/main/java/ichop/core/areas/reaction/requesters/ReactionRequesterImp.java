package ichop.core.areas.reaction.requesters;

import com.fasterxml.jackson.databind.ObjectMapper;
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

}
