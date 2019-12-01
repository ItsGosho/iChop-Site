package com.ichop.plugin.linkaccount.commons.repository;

import com.ichop.plugin.linkaccount.domain.entities.BaseEntity;
import com.ichop.plugin.linkaccount.repository.TransactionFunctional;

import javax.persistence.EntityManager;

@SuppressWarnings("all")
public abstract class AbstractRepository<E extends BaseEntity> implements Repository<E> {

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

    @Override
    public E save(E e) {
        return (E) this.execute((entityManager -> {
            entityManager.persist(e);
            return e;
        }));
    }

    @Override
    public void delete(E e) {
        this.execute((entityManager -> {
            entityManager.remove(e);
            return null;
        }));
    }
}
