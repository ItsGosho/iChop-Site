package com.ichop.core.repository.user;

import com.ichop.core.domain.entities.users.User;
import com.ichop.core.domain.entities.users.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation,String> {

    UserInformation findByUser(User user);

}
