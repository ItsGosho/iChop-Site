package ichop.reactions.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.common.aop.JmsAfterReturn;
import ichop.reactions.common.aop.JmsValidate;
import ichop.reactions.common.helpers.BaseListener;
import ichop.reactions.common.helpers.JmsHelper;
import ichop.reactions.domain.models.jms.check.ReactionIsReactedReply;
import ichop.reactions.domain.models.jms.check.ReactionIsReactedRequest;
import ichop.reactions.domain.models.jms.create.ReactionCreateReply;
import ichop.reactions.domain.models.jms.create.ReactionCreateRequest;
import ichop.reactions.domain.models.service.ReactionServiceModel;
import ichop.reactions.services.ReactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import static ichop.reactions.common.constants.JmsFactories.QUEUE;
import static ichop.reactions.constants.ReactionReplyConstants.REACTION_CHECK_SUCCESSFUL;
import static ichop.reactions.constants.ReactionReplyConstants.REACTION_CREATED_SUCCESSFUL;

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
    public ReactionCreateReply create(Message message) {
        ReactionCreateRequest requestModel = this.jmsHelper.getResultModel(message, ReactionCreateRequest.class);

        ReactionServiceModel reaction = this.objectMapper.convertValue(requestModel, ReactionServiceModel.class);

        return this.reactionServices.save(reaction, ReactionCreateReply.class);
    }

    @JmsValidate(model = ReactionIsReactedRequest.class)
    @JmsAfterReturn(message = REACTION_CHECK_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reactions.is_reacted}", containerFactory = QUEUE)
    public ReactionIsReactedReply isReacted(Message message) {
        ReactionIsReactedRequest requestModel = this.jmsHelper.getResultModel(message, ReactionIsReactedRequest.class);

        boolean isReacted = this.reactionServices.isReacted(requestModel.getUserId(), requestModel.getEntityId(), requestModel.getEntityType());

        return new ReactionIsReactedReply(isReacted);
    }

}
