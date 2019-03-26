package com.ichop.linkaccount.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichop.linkaccount.domain.models.service.BaseServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;


public abstract class BaseService<Entity, Repository extends JpaRepository<Entity, String>> {

    protected ObjectMapper objectMapper;
    protected Class<Entity> entity;
    protected Repository repository;

    public BaseService(ObjectMapper objectMapper, Repository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
        this.entity = this.setUpEntityClass();
    }


    public <ServiceModel extends BaseServiceModel> ServiceModel findById(String id, Class<ServiceModel> serviceModel) {
        Entity entity = this.repository.findById(id).orElse(null);
        ServiceModel result = this.objectMapper.convertValue(entity, serviceModel);

        return result;
    }

    public <ServiceModelReciever extends BaseServiceModel, ServiceModelToReturn extends BaseServiceModel> ServiceModelToReturn save(ServiceModelReciever serviceModel, Class<ServiceModelToReturn> serviceModelToReturn) {
        Entity entity = this.objectMapper.convertValue(serviceModel, this.entity);
        this.repository.save(entity);

        return this.objectMapper.convertValue(entity, serviceModelToReturn);
    }

    public <ServiceModel extends BaseServiceModel> void delete(ServiceModel serviceModel) {
        Entity entity = this.objectMapper.convertValue(serviceModel, this.entity);
        this.repository.delete(entity);
    }

    public void deleteById(String id) {
        Entity entity = this.repository.findById(id).orElse(null);
        this.repository.delete(entity);
    }

    public <ReturnModel extends BaseServiceModel> List<ReturnModel> findAll(Class<ReturnModel> returnModelClass) {
        return this.repository.findAll().stream().map(x -> this.objectMapper.convertValue(x, returnModelClass)).collect(Collectors.toList());
    }

    public <ReturnModel extends BaseServiceModel> Page<ReturnModel> findAll(Class<ReturnModel> returnModelClass, Pageable pageable) {
        return this.repository.findAll(pageable).map(x -> this.objectMapper.convertValue(x, returnModelClass));
    }

    public boolean existsById(String id) {
        return this.repository.existsById(id);
    }

    private Class<Entity> setUpEntityClass() {
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
