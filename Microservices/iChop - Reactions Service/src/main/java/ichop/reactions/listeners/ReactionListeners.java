package ichop.reactions.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.common.helpers.BaseListener;
import ichop.reactions.common.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactionListeners extends BaseListener {

    @Autowired
    protected ReactionListeners(JmsHelper jmsHelper, ObjectMapper objectMapper) {
        super(jmsHelper, objectMapper);
    }
}
