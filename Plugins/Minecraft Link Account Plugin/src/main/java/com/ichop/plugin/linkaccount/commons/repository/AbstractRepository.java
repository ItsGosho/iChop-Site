package com.ichop.plugin.linkaccount.commons.repository;

import com.ichop.plugin.linkaccount.domain.entities.BaseEntity;
import com.ichop.plugin.linkaccount.domain.entities.Link;
import com.ichop.plugin.linkaccount.repository.TransactionFunctional;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

@SuppressWarnings("all")
public abstract class AbstractRepository<E extends BaseEntity> implements Repository<E> {

    protected EntityManager entityManager;
    protected Class<E> entityClass;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityClass = (Class<E>) this.getGenericClass(0);
    }

    protected Object execute(TransactionFunctional command) {
        this.entityManager.getTransaction().begin();
        try {

            Object result = command.execute(this.entityManager);

            if (result != null) {
                this.entityManager.refresh(result);
            }

            this.entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public E save(E e) {
        return (E) this.execute((entityManager -> {
            entityManager.persist(e);
            entityManager.flush();
            return e;
        }));
    }

    @Override
    public void deleteById(String id) {
        this.execute((entityManager -> {
            E e = entityManager.find(this.entityClass, id);
            entityManager.remove(e);
            entityManager.flush();
            entityManager.clear();
            return null;
        }));
    }

    private Class<?> getGenericClass(Integer position) {
        return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[position];
    }
}
