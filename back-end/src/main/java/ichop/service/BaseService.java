package ichop.service;

import ichop.domain.models.service.BaseServiceModel;
import ichop.domain.models.service.log.LogServiceModel;
import ichop.events.LogEvent;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;


public abstract class BaseService<Entity, Repository extends JpaRepository<Entity, String>> {

    protected ApplicationEventPublisher applicationEventPublisher;
    protected ModelMapper modelMapper;
    protected Class<Entity> entity;
    protected Repository repository;

    public BaseService(ApplicationEventPublisher applicationEventPublisher,ModelMapper modelMapper, Repository repository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.entity = this.setUpEntityClass();
    }

    private void publishEvent(){

    }

    public <LogType extends Enum<LogType>,ServiceModel extends BaseServiceModel> void createLog(String message,ServiceModel serviceModel, LogType logType){
        LogServiceModel logServiceModel = new LogServiceModel();
        logServiceModel.set
        LogEvent logEvent = new LogEvent();
    }

    public <ServiceModel extends BaseServiceModel> ServiceModel findById(String id, Class<ServiceModel> serviceModel) {
        Entity entity = this.repository.findById(id).orElse(null);
        ServiceModel result = this.modelMapper.map(entity, serviceModel);

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
        Entity entity = this.repository.findById(id).orElse(null);
        this.repository.delete(entity);
    }

    public <ReturnModel extends BaseServiceModel> List<ReturnModel> findAll(Class<ReturnModel> returnModelClass) {
        return this.repository.findAll().stream().map(x -> this.modelMapper.map(x, returnModelClass)).collect(Collectors.toList());
    }

    public <ReturnModel extends BaseServiceModel> Page<ReturnModel> findAll(Class<ReturnModel> returnModelClass, Pageable pageable) {
        return this.repository.findAll(pageable).map(x -> this.modelMapper.map(x, returnModelClass));
    }

    public boolean existsById(String id){
        return this.repository.existsById(id);
    }

    private Class<Entity> setUpEntityClass() {
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
