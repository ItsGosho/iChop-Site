package org.ichop.commons.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ichop.commons.domain.MongoEntity;
import org.ichop.commons.domain.BaseServiceModel;
import org.ichop.commons.domain.MySQLEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseMySQLService
        <E extends MySQLEntity, S extends BaseServiceModel, R extends JpaRepository<E, String>>
        extends AbstractBaseService<E, S, R> {

    public BaseMySQLService(ObjectMapper objectMapper, R repository) {
        super(objectMapper, repository);
    }

}