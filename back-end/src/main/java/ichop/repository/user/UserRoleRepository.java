package ichop.repository.user;

import ichop.domain.entities.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {


    UserRole findUserRoleByAuthority(String authority);
}
