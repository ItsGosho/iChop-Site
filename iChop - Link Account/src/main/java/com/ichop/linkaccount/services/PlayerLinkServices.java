package com.ichop.linkaccount.services;

import com.ichop.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.linkaccount.domain.models.binding.PlayerUnlinkBindingModel;
import com.ichop.linkaccount.domain.models.service.PlayerLinkServiceModel;

public interface PlayerLinkServices {

    boolean linkToSiteUser(PlayerLinkCreateBindingModel bindingModel);
    boolean unlinkFromSiteUser(PlayerUnlinkBindingModel bindingModel);

    boolean isPlayerLinkExistBySiteUser(String username);

    PlayerLinkServiceModel getBySiteUser(String siteUserUsername);
    PlayerLinkServiceModel getByUUID(String uuid);

    boolean isAccountLinkedByUUID(String uuid);
    boolean isAccountLinkedBySiteUserUsername(String username);
}
