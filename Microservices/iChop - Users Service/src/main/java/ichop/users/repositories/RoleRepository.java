package ichop.users.repositories;

import ichop.users.domain.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role,String> {


    Role findUserRoleByAuthority(String authority);
}
