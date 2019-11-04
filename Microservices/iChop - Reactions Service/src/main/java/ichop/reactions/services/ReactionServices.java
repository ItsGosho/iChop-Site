package ichop.reactions.services;

import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.models.service.ReactionServiceModel;
import org.ichop.commons.service.BaseService;

public interface ReactionServices extends BaseService<ReactionServiceModel> {

    boolean isReacted(String userId, String entityId, EntityType entityType);

}
