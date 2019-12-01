package com.ichop.plugin.linkaccount.services;

import com.ichop.plugin.linkaccount.domain.models.service.LinkServiceModel;

public interface LinkServices {
    boolean isAccountLinkedByPlayerUUID(String uuid);

    boolean isAccountLinkedByCandidateUID(String uuid);

    LinkServiceModel findByPlayerUUID(String playerUUID);

    LinkServiceModel findByCandidateUID(String candidateUID);
}
