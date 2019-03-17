package ichop.service.thread.view;

import ichop.domain.models.view.home.ThreadHomepageViewModel;
import ichop.domain.models.view.thread_read.ThreadReadViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThreadViewServices {

    Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable);
    ThreadReadViewModel getThreadById(String id);

}
