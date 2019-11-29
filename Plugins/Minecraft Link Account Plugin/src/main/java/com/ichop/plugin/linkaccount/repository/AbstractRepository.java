package com.ichop.plugin.linkaccount.repository;

import com.ichop.plugin.linkaccount.domain.entities.Key;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class AbstractRepository {

    private final EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected Object execute(TransactionFunctional command) {
        this.entityManager.getTransaction().begin();
        try {

            Object result = command.execute(this.entityManager);

            this.entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }
}
