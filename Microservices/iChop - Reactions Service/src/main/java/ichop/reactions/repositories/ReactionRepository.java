package ichop.reactions.repositories;

import ichop.reactions.domain.entities.Reaction;
import ichop.reactions.domain.enums.EntityType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends MongoRepository<Reaction, String> {

    boolean existsByCreatorUsernameAndEntityIdAndEntityType(String creatorUsername, String entityId, EntityType entityType);

}
