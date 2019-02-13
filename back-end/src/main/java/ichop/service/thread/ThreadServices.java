package ichop.service.thread;

import ichop.domain.entities.threads.Thread;
import ichop.domain.entities.users.User;
import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThreadServices {

    Thread create(ThreadCreateBindingModel threadCreateBindingModel, User user);

    Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable);

    void delete(String id);

    boolean exists(String id);
}
