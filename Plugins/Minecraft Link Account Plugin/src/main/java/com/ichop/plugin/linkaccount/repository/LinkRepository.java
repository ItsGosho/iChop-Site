package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.domain.entities.Link;

public interface LinkRepository {

    Link findByPlayerUUID(String playerUUID);
    Link findByCandidateUID(String candidateUID);

    Link save(Link key);
    void delete(Link key);
}
