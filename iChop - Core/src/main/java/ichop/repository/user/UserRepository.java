package ichop.repository.user;

import ichop.domain.entities.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User AS u\n" +
            "SET u.lastOnline = :dateTime\n" +
            "WHERE u = :user")
    void updateLastOnline(@Param(value = "user") User user, @Param(value = "dateTime") LocalDateTime localDateTime);


    @Transactional
    @Modifying
    @Query("UPDATE User AS u\n" +
            "SET u.location = :location\n" +
            "WHERE u = :user")
    void updateUserLocation(@Param(value = "user") User user, @Param(value = "location") String location);


    @Query(nativeQuery = true, value = "SELECT case when COUNT(uf.user_id) = 1 then 'true' ELSE 'false' END\n" +
            "FROM users_followers AS uf\n" +
            "WHERE uf.user_id = :userId AND uf.follower_id = :followingUserId")
    boolean isUserAlreadyFollowedUser(@Param(value = "userId") String userId, @Param(value = "followingUserId") String followingUserId);

    @Query(nativeQuery = true, value = "SELECT COUNT(uf.user_id)\n" +
            "FROM users_followers AS uf\n" +
            "WHERE uf.user_id = :userId")
    int getUserTotalFollowings(@Param(value = "userId") String userId);

    @Query(nativeQuery = true, value = "SELECT COUNT(uf.user_id)\n" +
            "FROM users_followers AS uf\n" +
            "WHERE uf.follower_id = :userId")
    int getUserTotalFollowers(@Param(value = "userId") String userId);

    @Query("SELECT u\n" +
            "FROM User AS u\n" +
            "WHERE u.username LIKE CONCAT('%',:containingWord,'%')")
    Page<User> findUsersByUsernameContains(@Param(value = "containingWord") String containingWord, Pageable pageable);

//    @Query(nativeQuery = true, value = "SELECT *\n" +
//            "FROM users AS u\n" +
//            "JOIN users_authorities AS ua ON ua.user_id = u.id\n" +
//            "JOIN users_roles AS ur ON ur.id = ua.authorities_id\n" +
//            "WHERE ur.authority = :role")
//    Page<User> findUsersWhomHasRole(@Param(value = "role") String role, Pageable pageable);

    @Query("SELECT u\n" +
            "FROM User AS u\n" +
            "JOIN u.authorities as au\n" +
            "WHERE au.authority = :role")
    Page<User> findUsersWhomHasRole(@Param(value = "role") String role, Pageable pageable);

}
