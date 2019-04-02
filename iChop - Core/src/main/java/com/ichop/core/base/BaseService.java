package com.ichop.core.base;

import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;


public abstract class BaseService<Entity, Repository extends JpaRepository<Entity, String>> {

    protected ApplicationEventPublisher applicationEventPublisher;
    protected ModelMapper modelMapper;
    protected Class<Entity> entity;
    protected Repository repository;

    public BaseService(ModelMapper modelMapper, Repository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.entity = this.setUpEntityClass();
    }

    public BaseService(ApplicationEventPublisher applicationEventPublisher, ModelMapper modelMapper, Repository repository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.entity = this.setUpEntityClass();
    }

    private void publishEvent(Object event) {

        if (this.applicationEventPublisher == null) {
            throw new IllegalArgumentException("The provided class must extend BaseService with the ApplicationEventPublisher constructor!");
        }

        this.applicationEventPublisher.publishEvent(event);

    }


    public <Event extends ApplicationEvent> void createEvent(Class<Event> event, Object... data) {
        try {

            Class<?>[] dataTypes = new Class[data.length];

            dataTypes[0] = Object.class;

            for (int i = 1; i < data.length; i++) {
                dataTypes[i] = data[i].getClass();
            }

            Constructor constructor = event.getConstructor(dataTypes);

            Object readyEvent = constructor.newInstance(data);
            this.publishEvent(readyEvent);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public <ServiceModel extends BaseServiceModel> ServiceModel findById(String id, Class<ServiceModel> serviceModel) {
        Entity entity = this.repository.findById(id).orElse(null);
        ServiceModel result = entity != null ? this.modelMapper.map(entity, serviceModel) : null;

        return result;
    }

    public <ServiceModelReciever extends BaseServiceModel, ServiceModelToReturn extends BaseServiceModel> ServiceModelToReturn save(ServiceModelReciever serviceModel, Class<ServiceModelToReturn> serviceModelToReturn) {
        Entity entity = this.modelMapper.map(serviceModel, this.entity);
        this.repository.save(entity);

        return this.modelMapper.map(entity, serviceModelToReturn);
    }

    public <ServiceModel extends BaseServiceModel> void delete(ServiceModel serviceModel) {
        Entity entity = this.modelMapper.map(serviceModel, this.entity);
        this.repository.delete(entity);
    }

    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    public <ReturnModel extends BaseServiceModel> List<ReturnModel> findAll(Class<ReturnModel> returnModelClass) {
        return this.repository.findAll().stream().map(x -> this.modelMapper.map(x, returnModelClass)).collect(Collectors.toList());
    }

    public <ReturnModel extends BaseServiceModel> Page<ReturnModel> findAll(Class<ReturnModel> returnModelClass, Pageable pageable) {
        return this.repository.findAll(pageable).map(x -> this.modelMapper.map(x, returnModelClass));
    }

    public boolean existsById(String id) {
        return this.repository.existsById(id);
    }

    private Class<Entity> setUpEntityClass() {
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
