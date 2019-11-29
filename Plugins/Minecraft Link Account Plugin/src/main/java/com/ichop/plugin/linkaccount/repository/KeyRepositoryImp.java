package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.domain.entities.Key;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class KeyRepositoryImp extends AbstractRepository implements KeyRepository {

    @Inject
    public KeyRepositoryImp(EntityManager entityManager) {
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
                .createQuery("SELECT k FROM Key k WHERE k.key = :key", Key.class)
                .setParameter("key", key)
                .getSingleResult());
    }

    @Override
    public Key save(Key key) {
        return (Key) super.execute((entityManager -> {
            entityManager.persist(key);
            return key;
        }));
    }

    @Override
    public void delete(Key key) {
        super.execute((entityManager -> {
            entityManager.remove(key);
            return null;
        }));
    }
}
