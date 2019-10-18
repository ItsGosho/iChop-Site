package ichop.threads.services;

import ichop.threads.domain.models.service.ThreadServiceModel;

public interface ThreadServices extends BaseService<ThreadServiceModel> {

    void increaseViews(String id);

}
