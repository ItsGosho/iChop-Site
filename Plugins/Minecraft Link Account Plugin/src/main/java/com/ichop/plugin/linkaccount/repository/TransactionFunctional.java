package com.ichop.plugin.linkaccount.repository;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface TransactionFunctional {

    Object execute(EntityManager entityManager);

}
