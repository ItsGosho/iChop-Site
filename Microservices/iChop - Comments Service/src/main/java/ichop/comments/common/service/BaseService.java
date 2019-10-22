package ichop.comments.common.service;

import ichop.comments.common.domain.BaseServiceModel;

import java.util.List;

public interface BaseService<S extends BaseServiceModel> {

    <M> M findById(String id, Class<M> serviceModel);

    S findById(String id);

    <M> M save(S serviceModel, Class<M> returnModel);

    S save(S serviceModel);

    void delete(S serviceModel);

    void deleteById(String id);

    <M> List<M> findAll(Class<M> returnModelClass);

    boolean existsById(String id);
}