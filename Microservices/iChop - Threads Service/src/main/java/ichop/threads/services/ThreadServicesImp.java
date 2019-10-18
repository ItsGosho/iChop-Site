package ichop.threads.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ichop.threads.domain.entities.Thread;
import ichop.threads.domain.models.service.ThreadServiceModel;
import ichop.threads.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadServicesImp extends AbstractBaseService<Thread, ThreadServiceModel, ThreadRepository> implements ThreadServices {

    @Autowired
    public ThreadServicesImp(ObjectMapper objectMapper, ThreadRepository repository) {
        super(objectMapper, repository);
    }

    @Override
    public void increaseViews(String id) {
        ThreadServiceModel thread = super.findById(id);
        thread.setViews(thread.getViews() + 1);

        super.save(thread);
    }
}
