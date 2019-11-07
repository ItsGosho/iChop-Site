package ichop.reactions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.domain.entities.Reaction;
import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.models.service.ReactionServiceModel;
import ichop.reactions.repositories.ReactionRepository;
import org.ichop.commons.service.BaseMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionServicesImp
        extends BaseMongoService<Reaction, ReactionServiceModel, ReactionRepository>
        implements ReactionServices {


    @Autowired
    public ReactionServicesImp(ObjectMapper objectMapper, ReactionRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public boolean isReacted(String creatorUsername, String entityId, EntityType entityType) {
        return super.repository.existsByCreatorUsernameAndEntityIdAndEntityType(creatorUsername,entityId,entityType);
    }

}
