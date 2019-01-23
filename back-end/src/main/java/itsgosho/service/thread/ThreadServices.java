package itsgosho.service.thread;

import itsgosho.domain.models.binding.thread.ThreadCreateBindingModel;

public interface ThreadServices {
    void createThread(ThreadCreateBindingModel threadCreateBindingModel, String creatorUsername);
}
