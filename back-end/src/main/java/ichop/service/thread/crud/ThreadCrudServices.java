package ichop.service.thread.crud;

import ichop.domain.entities.threads.Thread;
import ichop.domain.models.service.ThreadServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThreadCrudServices {

    ThreadServiceModel getThread(String id);

    void delete(String id);

    boolean exists(String id);

    ThreadServiceModel save(ThreadServiceModel threadServiceModel);

    List<ThreadServiceModel> findAll();

    Page<Thread> findAll(Pageable pageable);
}
