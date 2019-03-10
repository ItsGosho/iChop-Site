package ichop.service.threads.thread;

import ichop.domain.models.binding.thread.ThreadCreateBindingModel;
import ichop.domain.models.service.threads.thread.ThreadServiceModel;
import ichop.domain.models.service.user.UserServiceModel;

public interface ThreadServices {

    ThreadServiceModel create(ThreadCreateBindingModel threadCreateBindingModel, UserServiceModel user);

}
