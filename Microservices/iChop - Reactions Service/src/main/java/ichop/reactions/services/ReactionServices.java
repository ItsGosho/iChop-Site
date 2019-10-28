package ichop.reactions.services;

import ichop.reactions.common.service.BaseService;
import ichop.reactions.domain.enums.EntityType;
import ichop.reactions.domain.models.service.ReactionServiceModel;

public interface ReactionServices extends BaseService<ReactionServiceModel> {

    boolean isReacted(String userId, String entityId, EntityType entityType);

}
