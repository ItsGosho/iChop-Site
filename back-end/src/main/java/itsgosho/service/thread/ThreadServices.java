package itsgosho.service.thread;

import itsgosho.domain.models.binding.thread.ThreadCreateBindingModel;
import itsgosho.domain.models.view.thread.ThreadHomepageViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThreadServices {
    void create(ThreadCreateBindingModel threadCreateBindingModel, String creatorUsername);

    Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable);

    void delete(String id);

    boolean exists(String id);
}
