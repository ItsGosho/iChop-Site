package ichop.core.areas.user.repositories;

import ichop.core.areas.user.domain.entities.User;
import ichop.core.areas.user.domain.entities.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow,String> {


    @Query(value = "SELECT uf\n" +
            "FROM UserFollow AS uf\n" +
            "WHERE uf.user = :user AND uf.follower = :follower")
    UserFollow findByUserAndHisFollower(@Param(value = "user") User user, @Param(value = "follower") User follower);

    @Query(value = "SELECT (case when count(uf.user) >= 1 then 'true' else 'false' end)\n" +
            "FROM UserFollow AS uf\n" +
            "WHERE uf.follower = :user AND\n" +
            "uf.user = :follower")
    boolean isUserAlreadyFollowedUser(@Param(value = "user") User user, @Param(value = "follower") User follower);

    @Query(value = "SELECT uf\n" +
            "FROM UserFollow AS uf\n" +
            "WHERE uf.follower = :user")
    List<UserFollow> findUserFollowings(@Param(value = "user") User user);

    @Query(value = "SELECT uf\n" +
            "FROM UserFollow AS uf\n" +
            "WHERE uf.user = :user")
    List<UserFollow> findUserFollowers(@Param(value = "user") User user);

    @Query(value = "SELECT COUNT(uf.id)\n" +
            "FROM UserFollow AS uf\n" +
            "WHERE uf.follower = :user")
    int getUserTotalFollowings(@Param(value = "user") User user);

    @Query(value = "SELECT COUNT(uf.id)\n" +
            "FROM UserFollow AS uf\n" +
            "WHERE uf.user = :user")
    int getUserTotalFollowers(@Param(value = "user") User user);


}
