package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.commons.repository.AbstractRepository;
import com.ichop.plugin.linkaccount.domain.entities.Link;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class LinkRepositoryImpl extends AbstractRepository<Link> implements LinkRepository {

    @Inject
    public LinkRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Link findByPlayerUUID(String playerUUID) {
        return (Link) super.execute((entityManager) -> entityManager
                .createQuery("SELECT l FROM Link l WHERE l.playerUUID = :playerUUID", Link.class)
                .setParameter("playerUUID", playerUUID)
                .getSingleResult());
    }

    @Override
    public Link findByCandidateUID(String candidateUID) {
        return (Link) super.execute((entityManager) -> entityManager
                .createQuery("SELECT l FROM Link l WHERE l.candidateUID = :candidateUID", Link.class)
                .setParameter("candidateUID", candidateUID)
                .getSingleResult());
    }
}
