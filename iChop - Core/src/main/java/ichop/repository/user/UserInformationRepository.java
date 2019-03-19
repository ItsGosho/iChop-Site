package ichop.repository.user;

import ichop.domain.entities.users.User;
import ichop.domain.entities.users.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation,String> {

    UserInformation findByUser(User user);

}
