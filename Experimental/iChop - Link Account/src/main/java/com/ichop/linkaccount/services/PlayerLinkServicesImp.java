package com.ichop.linkaccount.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.domain.entities.PlayerLink;
import com.ichop.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.linkaccount.domain.models.binding.PlayerUnlinkBindingModel;
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

        this.save(playerLink, PlayerLinkServiceModel.class);
        this.keyServices.deleteByUUID(playerLink.getPlayerUUID());

        return true;
    }

    @Override
    public boolean unlinkFromSiteUser(PlayerUnlinkBindingModel bindingModel) {

        if (!this.isAccountLinkedBySiteUserUsername(bindingModel.getSiteUserUsername())) {
            return false;
        }

        PlayerLink playerLink = this.repository.getBySiteUserUsername(bindingModel.getSiteUserUsername());
        this.repository.delete(playerLink);

        return true;
    }

    @Override
    public boolean isPlayerLinkExistBySiteUser(String username) {
        return this.repository.existsBySiteUserUsername(username);
    }

    @Override
    public PlayerLinkServiceModel getBySiteUser(String siteUserUsername) {
        PlayerLink playerLink = this.repository.getBySiteUserUsername(siteUserUsername);
        return this.objectMapper.convertValue(playerLink, PlayerLinkServiceModel.class);
    }

    @Override
    public PlayerLinkServiceModel getByUUID(String uuid) {
        PlayerLink playerLink = this.repository.getByPlayerUUID(uuid);
        return this.objectMapper.convertValue(playerLink, PlayerLinkServiceModel.class);
    }

    @Override
    public boolean isAccountLinkedByUUID(String uuid) {

        if (this.repository.getByPlayerUUID(uuid) != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isAccountLinkedBySiteUserUsername(String username) {

        if (this.repository.getBySiteUserUsername(username) != null) {
            return true;
        }

        return false;
    }

}
