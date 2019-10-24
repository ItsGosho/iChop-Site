package ichop.core.areas.user.repositories;

import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation,String> {

    UserInformation findByUser(User user);

}
