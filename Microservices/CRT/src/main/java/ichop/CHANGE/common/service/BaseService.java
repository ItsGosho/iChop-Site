package ichop.CHANGE.common.service;

import ichop.threads.common.domain.BaseServiceModel;

import java.util.List;

public interface BaseService<S extends BaseServiceModel> {

    S findById(String id, Class<S> serviceModel);

    S findById(String id);

    S save(S serviceModel, Class<S> returnModel);

    S save(S serviceModel);

    void delete(S serviceModel);

    void deleteById(String id);

    List<S> findAll(Class<S> returnModelClass);

    boolean existsById(String id);
}