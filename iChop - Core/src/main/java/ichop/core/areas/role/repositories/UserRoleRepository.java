package ichop.core.areas.role.repositories;

import ichop.core.areas.role.domain.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {


    UserRole findUserRoleByAuthority(String authority);
}
