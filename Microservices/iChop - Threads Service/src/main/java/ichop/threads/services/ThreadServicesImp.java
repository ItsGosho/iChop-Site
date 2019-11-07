package ichop.threads.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.domain.entities.Thread;
import ichop.threads.domain.models.service.ThreadServiceModel;
import ichop.threads.repositories.ThreadRepository;
import org.ichop.commons.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadServicesImp extends AbstractBaseService<Thread, ThreadServiceModel, ThreadRepository> implements ThreadServices {

    @Autowired
    public ThreadServicesImp(ObjectMapper objectMapper, ThreadRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public ThreadServiceModel increaseViews(String id) {
        ThreadServiceModel thread = super.findById(id);
        thread.setViews(thread.getViews() + 1);

        return super.save(thread);
    }

    @Override
    public <M> M increaseViews(String id, Class<M> clazz) {
        return super.objectMapper.convertValue(this.increaseViews(id),clazz);
    }
}
