package com.ichop.minecraft.linkaccount.services;

import com.ichop.minecraft.linkaccount.domain.models.binding.KeyCreateBindingModel;
import com.ichop.minecraft.linkaccount.domain.models.service.KeyServiceModel;

public interface KeyServices {

    boolean isKeyExpired(String key);

    boolean isKeyValid(String key);

    KeyServiceModel getByKey(String key);

    KeyServiceModel getByPlayerUUID(String playerUUID);
}
