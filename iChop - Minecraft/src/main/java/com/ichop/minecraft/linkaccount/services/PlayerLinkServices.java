package com.ichop.minecraft.linkaccount.services;

import com.ichop.minecraft.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.minecraft.linkaccount.domain.models.service.PlayerLinkServiceModel;

public interface PlayerLinkServices {

    boolean linkToSiteUser(PlayerLinkCreateBindingModel playerLinkCreateBindingModel);
    boolean unlinkFromSiteUser(PlayerLinkServiceModel playerLink);

    boolean isAccountLinkedByUUID(String uuid);
    boolean isAccountLinkedBySiteUserUsername(String username);
}
