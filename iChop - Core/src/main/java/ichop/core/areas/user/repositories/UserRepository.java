package ichop.core.areas.user.repositories;

import ichop.core.areas.user.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User AS u\n" +
            "SET u.lastOnline = :dateTime\n" +
            "WHERE u = :user")
    void updateLastOnline(@Param(value = "user") User user, @Param(value = "dateTime") LocalDateTime localDateTime);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User AS u\n" +
            "SET u.location = :location\n" +
            "WHERE u = :user")
    void updateLocation(@Param(value = "user") User user, @Param(value = "location") String location);

    @Query("SELECT u\n" +
            "FROM User AS u\n" +
            "WHERE u.username LIKE CONCAT('%',:containingWord,'%')")
    Page<User> findUsersByUsernameContains(@Param(value = "containingWord") String containingWord, Pageable pageable);

    @Query("SELECT u\n" +
            "FROM User AS u\n" +
            "JOIN u.authorities as au\n" +
            "WHERE au.authority = :role")
    List<User> findUsersWhomHasRole(@Param(value = "role") String role);

    @Query("SELECT u\n" +
            "FROM User AS u\n" +
            "JOIN u.authorities as au\n" +
            "WHERE au.authority = :role")
    Page<User> findUsersWhomHasRole(@Param(value = "role") String role, Pageable pageable);

}
