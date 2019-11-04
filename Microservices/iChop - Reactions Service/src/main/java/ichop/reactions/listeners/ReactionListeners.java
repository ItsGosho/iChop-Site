package ichop.reactions.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.domain.models.jms.ReactionReply;
import ichop.reactions.domain.models.jms.check.ReactionIsReactedRequest;
import ichop.reactions.domain.models.jms.create.ReactionCreateRequest;
import ichop.reactions.domain.models.service.ReactionServiceModel;
import ichop.reactions.services.ReactionServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.reactions.constants.ReactionReplyConstants.REACTION_CHECK_SUCCESSFUL;
import static ichop.reactions.constants.ReactionReplyConstants.REACTION_CREATED_SUCCESSFUL;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class ReactionListeners extends BaseListener {

    private final ReactionServices reactionServices;

    @Autowired
    protected ReactionListeners(JmsHelper jmsHelper, ObjectMapper objectMapper, ReactionServices reactionServices) {
        super(jmsHelper, objectMapper);
        this.reactionServices = reactionServices;
    }


    @JmsValidate(model = ReactionCreateRequest.class)
    @JmsAfterReturn(message = REACTION_CREATED_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reactions.create}", containerFactory = QUEUE)
    public ReactionReply create(Message message) {
        ReactionCreateRequest requestModel = this.jmsHelper.toModel(message, ReactionCreateRequest.class);

        ReactionServiceModel reaction = this.objectMapper.convertValue(requestModel, ReactionServiceModel.class);

        return this.reactionServices.save(reaction, ReactionReply.class);
    }

    @JmsValidate(model = ReactionIsReactedRequest.class)
    @JmsAfterReturn(message = REACTION_CHECK_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reactions.is.reacted}", containerFactory = QUEUE)
    public BoolReply isReacted(Message message) {
        ReactionIsReactedRequest requestModel = this.jmsHelper.toModel(message, ReactionIsReactedRequest.class);

        boolean isReacted = this.reactionServices.isReacted(requestModel.getUserId(), requestModel.getEntityId(), requestModel.getEntityType());

        return new BoolReply(isReacted);
    }

}
