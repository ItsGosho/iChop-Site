package com.ichop.plugin.linkaccount.services;

import com.ichop.plugin.linkaccount.domain.models.binding.KeyCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;

public interface KeyServices {

    KeyServiceModel create(KeyCreateBindingModel keyCreateBindingModel);

    boolean isKeyExpired(String key);

    boolean isAccountLinked(String uuid);
}
