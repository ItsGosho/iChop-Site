package ichop.users.repositories;

import ichop.users.domain.entities.User;
import ichop.users.domain.entities.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, String> {


    UserFollow findByUserAndFollower(User user, User follower);

    @Query(value = "SELECT (case when count(uf.user) >= 1 then 'true' else 'false' end)\n" +
            "FROM UserFollow AS uf\n" +
            "WHERE uf.follower = :user AND\n" +
            "uf.user = :follower")
    boolean isUserAlreadyFollowedUser( User user, User follower);

    List<UserFollow> findAllByFollower(User user);

    List<UserFollow> findAllByUser(User user);

    Long countByFollower(User user);

    Long countByUser(User user);


}
