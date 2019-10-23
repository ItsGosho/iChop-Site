package ichop.reactions.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.reactions.common.service.AbstractBaseService;
import ichop.reactions.domain.entities.Reaction;
import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.models.service.ReactionServiceModel;
import ichop.reactions.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionServicesImp
        extends AbstractBaseService<Reaction, ReactionServiceModel, ReactionRepository>
        implements ReactionServices {


    @Autowired
    public ReactionServicesImp(ObjectMapper objectMapper, ReactionRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public boolean hasReacted(String userId, String entityId, EntityType entityType) {
        return super.repository.existsByUserIdAndAndEntityIdAndEntityType(userId,entityId,entityType);
    }

}
