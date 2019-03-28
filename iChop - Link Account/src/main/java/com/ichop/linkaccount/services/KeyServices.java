package com.ichop.linkaccount.services;

import com.ichop.linkaccount.domain.models.service.KeyServiceModel;

public interface KeyServices {

    boolean isKeyExpired(String key);

    boolean isKeyValid(String key);

    KeyServiceModel getByKey(String key);

    KeyServiceModel getByPlayerUUID(String playerUUID);

    void deleteByUUID(String uuid);
}
