package ichop.user.areas.role.repositories;

import ichop.user.areas.role.domain.entities.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole,String> {


    UserRole findUserRoleByAuthority(String authority);
}
