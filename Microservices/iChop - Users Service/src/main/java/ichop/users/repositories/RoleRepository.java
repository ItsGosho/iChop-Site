package ichop.users.repositories;

import ichop.users.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {


    Role findUserRoleByAuthority(String authority);
}
