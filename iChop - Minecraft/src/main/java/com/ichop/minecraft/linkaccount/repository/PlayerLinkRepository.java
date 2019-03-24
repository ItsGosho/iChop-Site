package com.ichop.minecraft.linkaccount.repository;

import com.ichop.minecraft.linkaccount.domain.entities.PlayerLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerLinkRepository extends JpaRepository<PlayerLink,String> {

    PlayerLink getByPlayerUUID(String uuid);
    PlayerLink getBySiteUserUsername(String siteUserUsername);

}
