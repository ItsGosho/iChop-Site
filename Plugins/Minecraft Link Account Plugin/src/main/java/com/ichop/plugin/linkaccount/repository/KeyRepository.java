package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.commons.repository.Repository;
import com.ichop.plugin.linkaccount.domain.entities.Key;

public interface KeyRepository extends Repository<Key> {

    Key findByPlayerUUID(String playerUUID);
    Key findByKey(String key);
}
