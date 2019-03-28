package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.domain.entities.Key;
import com.ichop.plugin.linkaccount.domain.models.service.KeyServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class KeyRepositoryImp implements KeyRepository {

    private final EntityManager entityManager;

    @Inject
    public KeyRepositoryImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Key findByPlayerUUID(String playerUUID) {
        this.entityManager.getTransaction().begin();
        try {
            Key result = this.entityManager
                    .createQuery("SELECT k FROM Key k WHERE k.playerUUID = :playerUUID", Key.class)
                    .setParameter("playerUUID", playerUUID)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Key findByKey(String key) {
        this.entityManager.getTransaction().begin();
        try {
            Key result = this.entityManager
                    .createQuery("SELECT k FROM Key k WHERE k.key = :key", Key.class)
                    .setParameter("key", key)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Key save(Key key) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(key);
            this.entityManager.getTransaction().commit();

            return key;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public void delete(Key key) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.remove(key);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        }
    }
}
