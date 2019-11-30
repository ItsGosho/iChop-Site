package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.domain.entities.Link;

public interface LinkRepository extends Repository<Link> {

    Link findByPlayerUUID(String playerUUID);
    Link findByCandidateUID(String candidateUID);
}
