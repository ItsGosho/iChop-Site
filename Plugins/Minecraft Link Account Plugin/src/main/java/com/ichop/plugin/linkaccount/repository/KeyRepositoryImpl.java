package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.commons.repository.AbstractRepository;
import com.ichop.plugin.linkaccount.domain.entities.Key;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class KeyRepositoryImpl extends AbstractRepository<Key> implements KeyRepository {

    @Inject
    public KeyRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Key findByPlayerUUID(String playerUUID) {
        return (Key) super.execute((entityManager) -> entityManager
                .createQuery("SELECT k FROM Key k WHERE k.playerUUID = :playerUUID", Key.class)
                .setParameter("playerUUID", playerUUID)
                .getSingleResult());
    }

    @Override
    public Key findByKey(String key) {
        return (Key) super.execute((entityManager) -> entityManager
                .createQuery("SELECT k FROM Key k WHERE k.linkKey = :key", Key.class)
                .setParameter("key", key)
                .getSingleResult());
    }
}
