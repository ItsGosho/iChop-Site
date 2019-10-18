package ichop.threads.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.domain.entities.BaseEntity;
import ichop.threads.domain.models.service.BaseServiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public abstract class AbstractBaseService<E extends BaseEntity, S extends BaseServiceModel, R extends MongoRepository<E, String>> implements BaseService<S> {

    protected ObjectMapper objectMapper;
    protected R repository;

    protected Class<E> entityClass;
    protected Class<S> serviceModelClass;

    public AbstractBaseService(ObjectMapper objectMapper, R repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;

        this.entityClass = (Class<E>) this.getGenericClass(0);
        this.serviceModelClass = (Class<S>) this.getGenericClass(1);
    }

    @Override
    public S findById(String id, Class<S> serviceModel) {
        E entity = this.repository.findById(id).orElse(null);
        S result = entity != null ? this.objectMapper.convertValue(entity, serviceModel) : null;

        return result;
    }

    @Override
    public S findById(String id) {
        E entity = this.repository.findById(id).orElse(null);
        S result = entity != null ? this.objectMapper.convertValue(entity, this.serviceModelClass) : null;

        return result;
    }

    @Override
    public S save(S serviceModel, Class<S> returnModel) {
        E entity = this.objectMapper.convertValue(serviceModel, this.entityClass);
        this.repository.save(entity);

        return this.objectMapper.convertValue(entity, returnModel);
    }

    @Override
    public S save(S serviceModel) {
        E entity = this.objectMapper.convertValue(serviceModel, this.entityClass);

        try {
            this.repository.save(entity);
        } catch (Exception ex) {
            return null;
        }

        return this.objectMapper.convertValue(entity, this.serviceModelClass);
    }

    @Override
    public void delete(S serviceModel) {
        this.repository.deleteById(serviceModel.getId());
    }

    @Override
    public void deleteById(String id) {
        if (this.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    @Override
    public List<S> findAll(Class<S> returnModelClass) {
        return this.repository.findAll().stream().map(x -> this.objectMapper.convertValue(x, returnModelClass)).collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        return this.repository.existsById(id);
    }

    protected Set<S> mapToSet(Collection collection) {
        Set<S> result = (Set<S>) collection
                .stream()
                .map(x -> this.objectMapper.convertValue(x, this.serviceModelClass))
                .collect(Collectors.toSet());

        return result;
    }

    protected List<S> mapToList(Collection collection) {
        List<S> result = (List<S>) collection
                .stream()
                .map(x -> this.objectMapper.convertValue(x, this.serviceModelClass))
                .collect(Collectors.toList());

        return result;
    }


    private Class<?> getGenericClass(Integer position) {
        return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[position];
    }

}