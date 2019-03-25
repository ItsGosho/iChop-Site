package com.ichop.minecraft.linkaccount.services;

import com.ichop.minecraft.linkaccount.domain.entities.PlayerLink;
import com.ichop.minecraft.linkaccount.domain.models.binding.PlayerLinkCreateBindingModel;
import com.ichop.minecraft.linkaccount.domain.models.service.PlayerLinkServiceModel;
import com.ichop.minecraft.linkaccount.repository.PlayerLinkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerLinkServicesImp extends BaseService<PlayerLink, PlayerLinkRepository> implements PlayerLinkServices {

    private final KeyServices keyServices;

    @Autowired
    public PlayerLinkServicesImp(ModelMapper modelMapper, PlayerLinkRepository repository, KeyServices keyServices) {
        super(modelMapper, repository);
        this.keyServices = keyServices;
    }

    @Override
    public boolean linkToSiteUser(PlayerLinkCreateBindingModel playerLinkCreateBindingModel) {

        PlayerLinkServiceModel playerLink = this.modelMapper.map(playerLinkCreateBindingModel, PlayerLinkServiceModel.class);

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
    public PlayerLinkServiceModel getBySiteUser(String siteUserUsername) {
        return this.modelMapper.map(super.repository.getBySiteUserUsername(siteUserUsername),PlayerLinkServiceModel.class);
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
