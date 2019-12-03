package com.ichop.plugin.linkaccount.services;

import com.ichop.plugin.linkaccount.domain.entities.Link;
import com.ichop.plugin.linkaccount.domain.models.binding.LinkCreateBindingModel;
import com.ichop.plugin.linkaccount.domain.models.service.LinkServiceModel;
import com.ichop.plugin.linkaccount.repository.LinkRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class LinkServicesImpl implements LinkServices {

    private final LinkRepository linkRepository;
    private final ModelMapper modelMapper;

    @Inject
    public LinkServicesImpl(LinkRepository linkRepository, ModelMapper modelMapper) {
        this.linkRepository = linkRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean isAccountLinkedByPlayerUUID(String uuid) {
        return this.findByPlayerUUID(uuid) != null;
    }

    @Override
    public boolean isAccountLinkedByCandidateUID(String candidateUID) {
        return this.findByCandidateUID(candidateUID) != null;
    }

    @Override
    public LinkServiceModel findByPlayerUUID(String playerUUID) {
        Link link = this.linkRepository.findByPlayerUUID(playerUUID);
        return link != null ? this.modelMapper.map(link, LinkServiceModel.class) : null;
    }

    @Override
    public LinkServiceModel findByCandidateUID(String candidateUID) {
        Link link = this.linkRepository.findByCandidateUID(candidateUID);
        return link != null ? this.modelMapper.map(link, LinkServiceModel.class) : null;
    }

    @Override
    public LinkServiceModel create(LinkCreateBindingModel bindingModel) {
        Link link = this.modelMapper.map(bindingModel, Link.class);
        link.setLinkedOn(LocalDateTime.now());

        link = this.linkRepository.save(link);

        return this.modelMapper.map(link, LinkServiceModel.class);
    }

    @Override
    public void unlinkByPlayerUUID(String playerUUID) {
        LinkServiceModel link = this.findByPlayerUUID(playerUUID);
        this.linkRepository.deleteById(link.getId());
    }
}
