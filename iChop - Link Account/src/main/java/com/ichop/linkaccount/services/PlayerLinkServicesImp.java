package com.ichop.linkaccount.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.domain.entities.PlayerLink;
import com.ichop.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.linkaccount.domain.models.service.PlayerLinkServiceModel;
import com.ichop.linkaccount.repositories.PlayerLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerLinkServicesImp extends BaseService<PlayerLink, PlayerLinkRepository> implements PlayerLinkServices {

    private final KeyServices keyServices;

    @Autowired
    public PlayerLinkServicesImp(ObjectMapper objectMapper, PlayerLinkRepository repository, KeyServices keyServices) {
        super(objectMapper, repository);
        this.keyServices = keyServices;
    }

    @Override
    public boolean linkToSiteUser(PlayerLinkCreateBindingModel playerLinkCreateBindingModel) {

        PlayerLinkServiceModel playerLink = this.objectMapper.convertValue(playerLinkCreateBindingModel, PlayerLinkServiceModel.class);

        if (this.isAccountLinkedByUUID(playerLink.getPlayerUUID())) {
            return false;
        }

        super.save(playerLink, PlayerLinkServiceModel.class);
        this.keyServices.deleteByUUID(playerLink.getPlayerUUID());

        return true;
    }

    @Override
    public boolean unlinkFromSiteUser(PlayerLinkServiceModel playerLink) {

        if (!this.isAccountLinkedByUUID(playerLink.getPlayerUUID())) {
            return false;
        }

        super.delete(playerLink);

        return true;
    }

    @Override
    public boolean isPlayerLinkExistBySiteUser(String username) {
        return super.repository.existsBySiteUserUsername(username);
    }

    @Override
    public PlayerLinkServiceModel getBySiteUser(String siteUserUsername) {
        return this.objectMapper.convertValue(super.repository.getBySiteUserUsername(siteUserUsername),PlayerLinkServiceModel.class);
    }

    @Override
    public boolean isAccountLinkedByUUID(String uuid) {

        if (super.repository.getByPlayerUUID(uuid) != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isAccountLinkedBySiteUserUsername(String username) {

        if(super.repository.getBySiteUserUsername(username) != null){
            return true;
        }

        return false;
    }

}
