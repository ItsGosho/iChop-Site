package ichop.service.thread;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.service.ThreadServiceModel;
import ichop.domain.models.service.UserServiceModel;
import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.domain.models.view.thread.ThreadReadViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThreadServices {

    ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel, UserServiceModel userServiceModel);

    Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable);

    ThreadServiceModel getThread(String id);

    void delete(String id);

    boolean exists(String id);
}
