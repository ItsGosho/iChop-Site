package ichop.service.thread.view;

import ichop.domain.models.view.thread.ThreadHomepageViewModel;
import ichop.domain.models.view.thread.ThreadReadViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThreadViewServices {

    Page<ThreadHomepageViewModel> listAllByPage(Pageable pageable);
    ThreadReadViewModel getThread(String id);

}
