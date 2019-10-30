package ichop.users.repositories;

import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, String> {

    UserInformation findByUser(User user);
    boolean existsByUser(User user);

}
