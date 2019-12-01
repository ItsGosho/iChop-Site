package com.ichop.plugin.linkaccount.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.plugin.linkaccount.domain.entities.Link;
import com.ichop.plugin.linkaccount.domain.models.service.LinkServiceModel;
import com.ichop.plugin.linkaccount.repository.LinkRepository;

import javax.inject.Inject;

public class LinkServicesImpl implements LinkServices {

    private final LinkRepository linkRepository;
    private final ObjectMapper objectMapper;

    @Inject
    public LinkServicesImpl(LinkRepository linkRepository, ObjectMapper objectMapper) {
        this.linkRepository = linkRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public boolean isAccountLinkedByPlayerUUID(String uuid) {
        return this.findByPlayerUUID(uuid) != null;
    }

    @Override
    public boolean isAccountLinkedByCandidateUID(String uuid) {
        return this.findByPlayerUUID(uuid) != null;
    }

    @Override
    public LinkServiceModel findByPlayerUUID(String playerUUID) {
        Link link = this.linkRepository.findByPlayerUUID(playerUUID);
        return link != null ? this.objectMapper.convertValue(link, LinkServiceModel.class) : null;
    }

    @Override
    public LinkServiceModel findByCandidateUID(String candidateUID) {
        Link link = this.linkRepository.findByCandidateUID(candidateUID);
        return link != null ? this.objectMapper.convertValue(link, LinkServiceModel.class) : null;
    }
}
