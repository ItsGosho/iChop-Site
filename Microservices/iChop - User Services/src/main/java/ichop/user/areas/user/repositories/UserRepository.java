package ichop.user.areas.user.repositories;

import ichop.user.areas.user.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

}
