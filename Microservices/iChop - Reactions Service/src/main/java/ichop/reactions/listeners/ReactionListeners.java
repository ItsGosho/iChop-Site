package ichop.reactions.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.common.helpers.BaseListener;
import ichop.reactions.common.helpers.JmsHelper;
import ichop.reactions.services.ReactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactionListeners extends BaseListener {

    private final ReactionServices reactionServices;

    @Autowired
    protected ReactionListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ReactionServices reactionServices) {
        super(jmsHelper, objectMapper);
        this.reactionServices = reactionServices;
    }
}
