package ichop.reactions.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.domain.entities.Reaction;
import ichop.reactions.domain.models.jms.ReactionReply;
import ichop.reactions.domain.models.jms.check.ReactionIsReactedRequest;
import ichop.reactions.domain.models.jms.create.ReactionCreateRequest;
import ichop.reactions.domain.models.jms.find.ReactionsFindByRequest;
import ichop.reactions.domain.models.service.ReactionServiceModel;
import ichop.reactions.helpers.CriteriaHelper;
import ichop.reactions.services.ReactionServices;
import org.ichop.commons.aop.JmsAfterReturn;
import org.ichop.commons.aop.JmsValidate;
import org.ichop.commons.domain.BoolReply;
import org.ichop.commons.helpers.BaseListener;
import org.ichop.commons.helpers.JmsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

import java.util.List;
import java.util.stream.Collectors;

import static ichop.reactions.constants.ReactionReplyConstants.*;
import static org.ichop.commons.constants.JmsFactories.QUEUE;

@Component
public class ReactionListeners extends BaseListener {

    private final ReactionServices reactionServices;
    private final CriteriaHelper criteriaHelper;
    private final MongoTemplate mongoTemplate;

    @Autowired
    protected ReactionListeners(JmsHelper jmsHelper,
                                ObjectMapper objectMapper,
                                ReactionServices reactionServices,
                                CriteriaHelper criteriaHelper,
                                MongoTemplate mongoTemplate) {
        super(jmsHelper, objectMapper);
        this.reactionServices = reactionServices;
        this.criteriaHelper = criteriaHelper;
        this.mongoTemplate = mongoTemplate;
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

        boolean isReacted = this.reactionServices.isReacted(requestModel.getCreatorUsername(), requestModel.getEntityId(), requestModel.getEntityType());

        return new BoolReply(isReacted);
    }

    @JmsValidate(model = ReactionsFindByRequest.class)
    @JmsAfterReturn(message = FETCH_SUCCESSFUL)
    @JmsListener(destination = "${artemis.queue.reactions.find.by}", containerFactory = QUEUE)
    public List<ReactionReply> findBy(Message message) {
        ReactionsFindByRequest requestModel = this.jmsHelper.toModel(message, ReactionsFindByRequest.class);

        Query query = this.criteriaHelper.createBy(requestModel);
        List<ReactionReply> result = this.mongoTemplate.find(query, Reaction.class)
                .stream()
                .map(x-> this.objectMapper.convertValue(x,ReactionReply.class))
                .collect(Collectors.toList());

        return result;
    }

}
