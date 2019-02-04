package ichop.repository.user;

import ichop.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
