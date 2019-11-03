package org.ichop.commons.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ichop.commons.domain.MongoEntity;
import org.ichop.commons.domain.BaseServiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;


public abstract class BaseMongoService
        <E extends MongoEntity, S extends BaseServiceModel, R extends MongoRepository<E, String>>
        extends AbstractBaseService<E, S, R> {

    public BaseMongoService(ObjectMapper objectMapper, R repository) {
        super(objectMapper, repository);
    }
}