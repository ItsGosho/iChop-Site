package ichop.users.repositories;

import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserFollow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowRepository extends MongoRepository<UserFollow, String> {


    UserFollow findByUserAndFollower(User user, User follower);

    @CUSTOM(value = "db.getCollection('test_foll').find({'follower':'toshko'}).count() > 0")
    boolean isUserAlreadyFollowedUser(@Param(value = "user") User user, @Param(value = "follower") User follower);

    List<UserFollow> findAllByFollower(@Param(value = "user") User user);

    List<UserFollow> findAllByUser(@Param(value = "user") User user);

    Long countByFollower(@Param(value = "user") User user);

    Long countByUser(@Param(value = "user") User user);


}
