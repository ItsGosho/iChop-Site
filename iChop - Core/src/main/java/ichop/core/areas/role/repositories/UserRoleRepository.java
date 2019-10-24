package ichop.core.areas.role.repositories;

import ichop.core.areas.role.domain.entities.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole,String> {


    UserRole findUserRoleByAuthority(String authority);
}
