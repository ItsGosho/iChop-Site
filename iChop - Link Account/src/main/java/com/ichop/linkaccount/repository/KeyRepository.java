package com.ichop.linkaccount.repository;

import com.ichop.linkaccount.domain.entities.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key,String> {

    Key findByPlayerUUID(String playerUUID);
    Key findByKey(String key);
}
