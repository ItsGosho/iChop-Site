package ichop.repository.user;

import ichop.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findUserByUsername(String username);
    User findUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User AS u\n" +
            "SET u.lastOnline = :dateTime\n" +
            "WHERE u = :user")
    void updateLastOnline(@Param(value = "user") User user,@Param(value = "dateTime") LocalDateTime localDateTime);


    @Transactional
    @Modifying
    @Query("UPDATE User AS u\n" +
            "SET u.location = :location\n" +
            "WHERE u = :user")
    void updateUserLocation(@Param(value = "user") User user,@Param(value = "location") String location);

    
}
