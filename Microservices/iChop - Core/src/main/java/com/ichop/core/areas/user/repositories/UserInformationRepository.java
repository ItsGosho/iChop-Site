package com.ichop.core.areas.user.repositories;

import com.ichop.core.areas.user.domain.entities.User;
import com.ichop.core.areas.user.domain.entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation,String> {

    UserInformation findByUser(User user);

}
