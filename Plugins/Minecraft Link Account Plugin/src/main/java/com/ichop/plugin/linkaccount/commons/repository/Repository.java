package com.ichop.plugin.linkaccount.commons.repository;

import com.ichop.plugin.linkaccount.domain.entities.BaseEntity;

public interface Repository<E extends BaseEntity> {

    E save(E e);
    void deleteById(String id);
}
