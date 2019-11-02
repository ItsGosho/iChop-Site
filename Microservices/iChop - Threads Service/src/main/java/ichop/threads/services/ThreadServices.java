package ichop.threads.services;

import ichop.threads.domain.models.service.ThreadServiceModel;
import org.ichop.commons.service.BaseService;

public interface ThreadServices extends BaseService<ThreadServiceModel> {

    ThreadServiceModel increaseViews(String id);
    <M> M increaseViews(String id,Class<M> clazz);

}
