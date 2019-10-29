package ichop.users.repositories;

import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends MongoRepository<UserInformation, String> {

    UserInformation findByUser(User user);
    boolean existsByUser(User user);

}
