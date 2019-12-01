package com.ichop.plugin.linkaccount.services;

import com.ichop.plugin.linkaccount.domain.models.binding.KeyCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;

public interface KeyServices {

    KeyServiceModel create(KeyCreateBindingModel keyCreateBindingModel);
    KeyServiceModel findByLinkKey(String linkKey);
    boolean isKeyExpired(String linkKey);
    boolean isValid(String linkKey);

    void deleteLastByUUID(String uuid);

    void deleteLastByKey(String linkKey);
}
