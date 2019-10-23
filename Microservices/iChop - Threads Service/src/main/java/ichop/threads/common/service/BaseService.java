package ichop.threads.common.service;

import ichop.threads.common.domain.BaseServiceModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<S extends BaseServiceModel> {

    <M> M findById(String id, Class<M> serviceModel);

    S findById(String id);

    <M> M save(S serviceModel, Class<M> returnModel);

    S save(S serviceModel);

    void delete(S serviceModel);

    void deleteById(String id);

    <M> List<M> findAll(Class<M> returnModelClass);

    List<S> findAll(Pageable pageable);

    <M> List<M> findAll(Pageable pageable, Class<M> returnModelClass);

    boolean existsById(String id);
}