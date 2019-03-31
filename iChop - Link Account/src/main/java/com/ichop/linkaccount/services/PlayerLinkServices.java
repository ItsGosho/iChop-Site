package com.ichop.linkaccount.services;

import com.ichop.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.linkaccount.domain.models.service.PlayerLinkServiceModel;

public interface PlayerLinkServices {

    boolean linkToSiteUser(PlayerLinkCreateBindingModel playerLinkCreateBindingModel);
    boolean unlinkFromSiteUser(PlayerLinkServiceModel playerLink);

    boolean isPlayerLinkExistBySiteUser(String username);

    PlayerLinkServiceModel getBySiteUser(String siteUserUsername);

    boolean isAccountLinkedByUUID(String uuid);
    boolean isAccountLinkedBySiteUserUsername(String username);
}
