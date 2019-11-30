package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.domain.entities.Key;

public interface KeyRepository {

    Key findByPlayerUUID(String playerUUID);
    Key findByKey(String key);

    Key save(Key key);
    void delete(Key key);
}
